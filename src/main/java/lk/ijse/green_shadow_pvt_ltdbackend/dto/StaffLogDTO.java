package lk.ijse.green_shadow_pvt_ltdbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffLogDTO implements Serializable {
    private String staffLogId;
    private String staffId;
    private String logId;
}
