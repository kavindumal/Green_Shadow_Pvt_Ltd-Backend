package lk.ijse.green_shadow_pvt_ltdbackend.dto.impl;

import lk.ijse.green_shadow_pvt_ltdbackend.customObj.UserResponse;
import lk.ijse.green_shadow_pvt_ltdbackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO, UserResponse {
    private String email;
    private String password;
    private String role;
}