package lk.ijse.green_shadow_pvt_ltdbackend.dao;

import lk.ijse.green_shadow_pvt_ltdbackend.entity.FieldCropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldCropDAO extends JpaRepository<FieldCropEntity, String> {
    void deleteByCrop_CropId(String cropId);
    List<FieldCropEntity> findByCrop_CropId(String cropId);
}
