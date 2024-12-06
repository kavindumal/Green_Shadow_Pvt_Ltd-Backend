package lk.ijse.green_shadow_pvt_ltdbackend.service;

import lk.ijse.green_shadow_pvt_ltdbackend.dto.UserDTO;
import lk.ijse.green_shadow_pvt_ltdbackend.jwtModel.JWTAuthResponse;
import lk.ijse.green_shadow_pvt_ltdbackend.jwtModel.SignIn;

public interface AuthenticationService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO signUp);
    JWTAuthResponse refreshToken(String accessToken);
}
