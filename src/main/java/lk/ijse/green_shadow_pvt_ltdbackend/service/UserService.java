package lk.ijse.green_shadow_pvt_ltdbackend.service;

import lk.ijse.green_shadow_pvt_ltdbackend.customObj.UserResponse;
import lk.ijse.green_shadow_pvt_ltdbackend.dto.impl.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDTO> getAllUsers();
}