package lk.ijse.green_shadow_pvt_ltdbackend.controller;

import lk.ijse.green_shadow_pvt_ltdbackend.customObj.UserResponse;
import lk.ijse.green_shadow_pvt_ltdbackend.dto.impl.UserDTO;
import lk.ijse.green_shadow_pvt_ltdbackend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_pvt_ltdbackend.exception.UserNotFoundException;
import lk.ijse.green_shadow_pvt_ltdbackend.service.UserService;
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
            UserDTO buildUserDTO = new UserDTO();

            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(password);
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
            userService.deleteUser(email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getSelectedUser(@PathVariable ("email") String userId){
        return userService.getSelectedUser(userId);
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
            var updateUser = new UserDTO();

            updateUser.setEmail(email);
            updateUser.setPassword(password);
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