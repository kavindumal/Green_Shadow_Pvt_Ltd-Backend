package lk.ijse.green_shadow_pvt_ltdbackend.service;

import lk.ijse.green_shadow_pvt_ltdbackend.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    void saveUser(UserDTO user);
    void updateUser(String email, UserDTO user);
    boolean searchUser(String email);
    boolean deleteUser(String email);
    UserDetailsService userDetailsService();
}
