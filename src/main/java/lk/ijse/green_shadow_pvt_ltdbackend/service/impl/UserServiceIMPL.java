package lk.ijse.green_shadow_pvt_ltdbackend.service.impl;

import lk.ijse.green_shadow_pvt_ltdbackend.dao.StaffDAO;
import lk.ijse.green_shadow_pvt_ltdbackend.dao.UserDAO;
import lk.ijse.green_shadow_pvt_ltdbackend.dto.UserDTO;
import lk.ijse.green_shadow_pvt_ltdbackend.entity.StaffEntity;
import lk.ijse.green_shadow_pvt_ltdbackend.entity.UserEntity;
import lk.ijse.green_shadow_pvt_ltdbackend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_pvt_ltdbackend.exception.NotFoundException;
import lk.ijse.green_shadow_pvt_ltdbackend.service.UserService;
import lk.ijse.green_shadow_pvt_ltdbackend.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    public void saveUser(UserDTO user) {
        user.setRole(UserDTO.Role.valueOf(getUserRole(user.getEmail()).name()));
        UserEntity savedUser = userDAO.save(mappingUtil.userConvertToEntity(user));
        if (savedUser != null) {
            System.out.println("User saved successfully");
        } else {
            throw new DataPersistFailedException("User save unsuccessful");
        }
    }

    @Override
    public void updateUser(String email, UserDTO user) {
        Optional<UserEntity> tmpUserEntity = userDAO.findByEmail(email);
        if (tmpUserEntity.isPresent()){
            UserEntity userEntity = mappingUtil.userConvertToEntity(user);
            tmpUserEntity.get().setPassword(userEntity.getPassword());
            System.out.println("User password updated successfully: " + tmpUserEntity.get());
        } else {
            throw new NotFoundException("User not found with email: " + email);
        }
    }

    @Override
    public boolean searchUser(String email) {
        Optional<StaffEntity> user = staffDAO.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public boolean deleteUser(String email) {
        Optional<UserEntity> user = userDAO.findByEmail(email);
        if (user.isPresent()) {
            userDAO.delete(user.get());
            return true;
        } else {
            throw new NotFoundException("User not found with email: " + email);
        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email -> userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }

    private StaffEntity.Role getUserRole(String email) {
        return staffDAO.findByEmail(email)
                .map(StaffEntity::getRole)
                .orElseThrow(() ->
                        new NotFoundException("User not found for email: " + email));
    }
}
