package dians.homework3.wines02.service;

import dians.homework3.wines02.dto.CredentialsDto;
import dians.homework3.wines02.dto.RegistrationDto;
import dians.homework3.wines02.dto.UserDto;
import dians.homework3.wines02.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity saveUser(RegistrationDto registrationDto);


    UserEntity findByUsername(String username);

    List<UserEntity> findAll();
//    void deleteById(Long userId);


    UserEntity findById(Long userId);

    void saveUpdate(UserEntity user);

    UserDto findByLogin(String issuer);

    UserDto login(CredentialsDto credentialsDto);

    UserEntity register(RegistrationDto registrationDto);
}
