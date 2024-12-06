package lk.ijse.green_shadow_pvt_ltdbackend.service;

import lk.ijse.green_shadow_pvt_ltdbackend.dto.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO field);
    void updateField(String id, FieldDTO field);
    FieldDTO searchField(String id);
    boolean deleteField(String id);
    List<FieldDTO> getAllFields();
}
