package lk.ijse.green_shadow_pvt_ltdbackend.controller;

import lk.ijse.green_shadow_pvt_ltdbackend.customObj.UserResponse;
import lk.ijse.green_shadow_pvt_ltdbackend.dto.impl.UserDTO;
import lk.ijse.green_shadow_pvt_ltdbackend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_pvt_ltdbackend.exception.UserNotFoundException;
import lk.ijse.green_shadow_pvt_ltdbackend.service.UserService;
import lk.ijse.green_shadow_pvt_ltdbackend.util.AesAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<Void> saveUser(
            @RequestPart("email") String email,
            @RequestPart ("password") String password,
            @RequestPart ("role") String role) {
        try {
            AesAlgorithm aesAlgorithm = new AesAlgorithm();
            UserDTO buildUserDTO = new UserDTO();

            buildUserDTO.setEmail(aesAlgorithm.encrypt(email));
            buildUserDTO.setPassword(aesAlgorithm.encrypt(password));
            buildUserDTO.setRole(role);

            userService.saveUser(buildUserDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable ("email") String email) {
        try {
            AesAlgorithm aesAlgorithm = new AesAlgorithm();

            userService.deleteUser(aesAlgorithm.encrypt(email));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getSelectedUser(@PathVariable ("email") String email) throws Exception {
        AesAlgorithm aesAlgorithm = new AesAlgorithm();
        return userService.getSelectedUser(aesAlgorithm.encrypt(email));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PatchMapping(value = "/{email}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUser(
            @PathVariable ("email") String email,
            @RequestPart("password") String password,
            @RequestPart ("role") String role
    ){
        try {
            AesAlgorithm aesAlgorithm = new AesAlgorithm();
            var updateUser = new UserDTO();

            updateUser.setEmail(aesAlgorithm.encrypt(email));
            updateUser.setPassword(aesAlgorithm.encrypt(password));
            updateUser.setRole(role);

            userService.updateUser(updateUser);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}