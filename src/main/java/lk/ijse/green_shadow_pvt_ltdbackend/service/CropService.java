package lk.ijse.green_shadow_pvt_ltdbackend.service;

import lk.ijse.green_shadow_pvt_ltdbackend.dto.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO crop);
    void updateCrop(String id, CropDTO crop);
    CropDTO searchCrop(String id);
    boolean deleteCrop(String id);
    List<CropDTO> getAllCrops();
}
