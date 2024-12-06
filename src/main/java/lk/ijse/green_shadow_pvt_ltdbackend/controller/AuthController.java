package lk.ijse.green_shadow_pvt_ltdbackend.controller;

import lk.ijse.green_shadow_pvt_ltdbackend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_pvt_ltdbackend.dto.UserDTO;
import lk.ijse.green_shadow_pvt_ltdbackend.jwtModel.JWTAuthResponse;
import lk.ijse.green_shadow_pvt_ltdbackend.jwtModel.SignIn;
import lk.ijse.green_shadow_pvt_ltdbackend.service.AuthenticationService;
import lk.ijse.green_shadow_pvt_ltdbackend.service.JWTService;
import lk.ijse.green_shadow_pvt_ltdbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> saveUser(@RequestBody UserDTO user) {
        if (user != null) {
            try {
                boolean exist = userService.searchUser(user.getEmail());
                if (exist) {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userService.saveUser(user);
                    logger.info("User saved successfully: " + user);
                    return ResponseEntity.ok(authenticationService.signUp(user));
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.severe("Failed to save user: " + user);
                System.out.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn sign) {
        logger.info("User signed in successfully: " + sign);
        return ResponseEntity.ok(authenticationService.signIn(sign));
    }

    @PostMapping("/validate-token")  // frontend eken ewan ekayi api genaratore karana ekayi 2ma ekada kiyala check karanwa
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            boolean isValid = !jwtService.isTokenExpired(token);
            if (isValid) {
                return ResponseEntity.ok("Token is valid");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has expired");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    @PostMapping("refresh")
    public ResponseEntity<JWTAuthResponse> refreshToken (@RequestParam("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}