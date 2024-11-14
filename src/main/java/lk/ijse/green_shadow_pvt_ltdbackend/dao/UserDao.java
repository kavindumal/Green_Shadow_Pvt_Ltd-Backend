package lk.ijse.green_shadow_pvt_ltdbackend.dao;

import lk.ijse.green_shadow_pvt_ltdbackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity,String> {
    UserEntity getUserEntityByEmail(String emailAddress);
}
