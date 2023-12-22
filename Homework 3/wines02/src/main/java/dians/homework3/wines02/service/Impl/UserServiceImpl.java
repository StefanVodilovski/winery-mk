package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.CredentialsDto;
import dians.homework3.wines02.dto.RegistrationDto;
import dians.homework3.wines02.dto.UserDto;
import dians.homework3.wines02.exception.AppException;
import dians.homework3.wines02.mapper.UserMapper;
import dians.homework3.wines02.model.Role;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.repository.RoleRepository;
import dians.homework3.wines02.repository.UserRepository;
import dians.homework3.wines02.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static dians.homework3.wines02.mapper.UserMapper.mapToUserDto;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserEntity saveUser(RegistrationDto registrationDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationDto.getUsername());
        userEntity.setEmail(registrationDto.getEmail());
        userEntity.setPhoneNumber(registrationDto.getPhoneNumber());
        userEntity.setAddress(registrationDto.getAddress());
        userEntity.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        userEntity.setRoles(Collections.singletonList(role));
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity findByUsername(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> "ROLE_USER".equals(role.getName())))
                .collect(Collectors.toList());
    }

//    @Override
//    public void deleteById(Long userId) {
////        UserEntity user = userRepository.getById(userId);
////        Role role = roleRepository.getReferenceById(1L);
////        user.getRoles().remove(role);
////        userRepository.deleteById(userId);
//    }

    @Override
    public UserEntity findById(Long userId) {
        return userRepository.getReferenceById(userId);
    }

    @Override
    public void saveUpdate(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserDto findByLogin(String username) {
        Optional<UserEntity> userEntity = Optional.ofNullable(userRepository.findFirstByUsername(username));
        return userEntity.map(UserMapper::mapToUserDto).orElse(null);
    }

    @Override
    public UserDto login(CredentialsDto credentialsDto) {
        Optional<UserEntity> userEntity = Optional.ofNullable(userRepository.findFirstByUsername(credentialsDto.getUsername()));
        if(userEntity.isPresent()) {
            if(passwordEncoder.matches(passwordEncoder.encode(credentialsDto.getPassword()), userEntity.get().getPassword())) {
                return mapToUserDto(userEntity.get());
            }
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserEntity register(RegistrationDto registrationDto) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(registrationDto.getUsername());

        if(userEntity.isPresent()) {
            throw  new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        return saveUser(registrationDto);
    }
}
