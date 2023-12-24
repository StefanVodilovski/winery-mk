package dians.homework3.wines02.service;

import dians.homework3.wines02.dto.RegistrationDto;
import dians.homework3.wines02.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    List<UserEntity> findAll();
    void deleteById(Long userId);

    void save(RegistrationDto user);

    UserEntity findById(Long userId);

}
