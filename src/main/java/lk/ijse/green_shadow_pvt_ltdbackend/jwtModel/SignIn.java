package lk.ijse.green_shadow_pvt_ltdbackend.jwtModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignIn {
    public String email;
    public String password;
}
