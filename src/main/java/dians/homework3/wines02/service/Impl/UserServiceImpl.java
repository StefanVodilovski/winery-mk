package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.RegistrationDto;
import dians.homework3.wines02.model.Role;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.repository.RoleRepository;
import dians.homework3.wines02.repository.UserRepository;
import dians.homework3.wines02.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> "ROLE_USER".equals(role.getName())))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long userId) {
//        UserEntity user = userRepository.getById(userId);
//        Role role = roleRepository.getReferenceById(1L);
//        user.getRoles().remove(role);
//        userRepository.deleteById(userId);
    }

    @Override
    public void save(RegistrationDto registrationDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(registrationDto.getId());
        userEntity.setUsername(registrationDto.getUsername());
        userEntity.setEmail(registrationDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userEntity.setAddress(registrationDto.getAddress());
        userEntity.setPhoneNumber(registrationDto.getPhoneNumber());
        Role role = roleRepository.findByName("ROLE_USER");
        userEntity.setRoles(Collections.singletonList(role));
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findById(Long userId) {
        return userRepository.getReferenceById(userId);
    }

}
