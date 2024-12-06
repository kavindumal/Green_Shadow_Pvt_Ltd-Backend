package lk.ijse.green_shadow_pvt_ltdbackend.service;

import lk.ijse.green_shadow_pvt_ltdbackend.dto.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staff);
    void updateStaff(String id, StaffDTO staff);
    boolean deleteStaff(String id);
    StaffDTO searchStaff(String id);
    List<StaffDTO> getAllStaffs();
}
