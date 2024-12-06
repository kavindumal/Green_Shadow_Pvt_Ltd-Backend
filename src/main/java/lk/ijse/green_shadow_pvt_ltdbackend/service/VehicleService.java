package lk.ijse.green_shadow_pvt_ltdbackend.service;

import lk.ijse.green_shadow_pvt_ltdbackend.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicle);
    void updateVehicle(String id, VehicleDTO vehicle);
    VehicleDTO searchVehicle(String id);
    boolean deleteVehicle(String id);
    List<VehicleDTO> getAllVehicles();
}
