package lk.ijse.green_shadow_pvt_ltdbackend.dao;

import lk.ijse.green_shadow_pvt_ltdbackend.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDAO extends JpaRepository<VehicleEntity, String> {}
