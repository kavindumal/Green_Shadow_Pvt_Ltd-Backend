package lk.ijse.green_shadow_pvt_ltdbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_pvt_ltdbackend.customObj.UserErrorResponse;
import lk.ijse.green_shadow_pvt_ltdbackend.customObj.UserResponse;
import lk.ijse.green_shadow_pvt_ltdbackend.dao.UserDao;
import lk.ijse.green_shadow_pvt_ltdbackend.dto.impl.UserDTO;
import lk.ijse.green_shadow_pvt_ltdbackend.entity.UserEntity;
import lk.ijse.green_shadow_pvt_ltdbackend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_pvt_ltdbackend.exception.UserNotFoundException;
import lk.ijse.green_shadow_pvt_ltdbackend.service.UserService;
import lk.ijse.green_shadow_pvt_ltdbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    @Autowired
    private final UserDao userDao;

    @Autowired
    private final Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity savedUser =
                userDao.save(mapping.convertToUserEntity(userDTO));
        if(savedUser == null ) {
            throw new DataPersistFailedException("Cannot data saved");
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userDao.findById(userDTO.getEmail());
        if(!tmpUser.isPresent()){
            throw new UserNotFoundException("User not found");
        }else {
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setRole(userDTO.getRole());
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> selectedUserId = userDao.findById(userId);
        if(!selectedUserId.isPresent()){
            throw new UserNotFoundException("User not found");
        }else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public UserResponse getSelectedUser(String emailAddress) {
        if(userDao.existsById(emailAddress)){
            UserEntity userEntityByUserEmail = userDao.getUserEntityByEmail(emailAddress);
            return mapping.convertToUserDTO(userEntityByUserEmail);
        }else {
            return new UserErrorResponse(0, "User not found");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> getAllUsers = userDao.findAll();
        return mapping.convertUserToDTOList(getAllUsers);
    }
}
