package lk.ijse.green_shadow_pvt_ltdbackend.service;

import lk.ijse.green_shadow_pvt_ltdbackend.dto.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipment);
    void updateEquipment(String id, EquipmentDTO equipment);
    EquipmentDTO searchEquipment(String id);
    boolean deleteEquipment(String id);
    List<EquipmentDTO> getAllEquipments();
}
