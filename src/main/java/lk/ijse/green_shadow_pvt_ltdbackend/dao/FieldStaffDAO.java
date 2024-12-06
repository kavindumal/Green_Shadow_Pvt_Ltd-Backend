package lk.ijse.green_shadow_pvt_ltdbackend.dao;

import lk.ijse.green_shadow_pvt_ltdbackend.entity.FieldStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldStaffDAO extends JpaRepository<FieldStaffEntity, String > {
    void deleteByField_FieldId(String fieldId);
    List<FieldStaffEntity> findByField_FieldId(String fieldId);
}
