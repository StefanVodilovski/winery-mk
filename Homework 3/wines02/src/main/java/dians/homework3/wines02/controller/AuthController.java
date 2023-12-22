package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.CredentialsDto;
import dians.homework3.wines02.dto.RegistrationDto;
import dians.homework3.wines02.dto.UserDto;
import dians.homework3.wines02.exception.AppException;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.security.SecurityUtil;
import dians.homework3.wines02.security.UserAuthProvider;
import dians.homework3.wines02.service.CartService;
import dians.homework3.wines02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dians.homework3.wines02.mapper.UserMapper.mapToUserDto;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final CartService cartService;
    private final UserAuthProvider userAuthProvider;

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    public static boolean isStrongPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&!@#$%^&*()-_=+\\[\\]{}|;:'\",.<>?/]{8,}$";

        return password.matches(passwordRegex);
    }

    @PostMapping("login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto user = userService.login(credentialsDto);
        user.setToken(userAuthProvider.createToken(user.getUsername()));

        return ResponseEntity.ok(user);
    }

    @PostMapping("register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid RegistrationDto registrationDto) {

        if(!isValidEmail(registrationDto.getEmail())) {
            throw new AppException("Incorrect email form.", HttpStatus.BAD_REQUEST);
        }
        if(!isStrongPassword(registrationDto.getPassword())) {
            throw new AppException("Your password is weak.", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = userService.register(registrationDto);
        cartService.save(userEntity);
        UserDto userDto = mapToUserDto(userEntity);
        userDto.setToken(userAuthProvider.createToken(registrationDto.getUsername()));

        return ResponseEntity.created(URI.create("/users/"+userDto.getId()))
                .body(userDto);
    }

    public UserEntity getSessionUserEntity() {
        return userService.findByUsername(SecurityUtil.getSessionUser());
    }

    @GetMapping("user/edit")
    private ResponseEntity<UserDto> editUser() {
        UserEntity user = getSessionUserEntity();

        return ResponseEntity.ok(mapToUserDto(user));
    }

    @PostMapping("user/edit")
    private ResponseEntity<UserDto> updateEditUser(@RequestParam String username,
                                @RequestParam String email,
                                @RequestParam MultipartFile profileImage,
                                @RequestParam String address) {
//        byte[] imageBytes = null;
//        if (profileImage != null && !profileImage.isEmpty()) {
//            try {
//                imageBytes = profileImage.getBytes();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        UserEntity user = getSessionUserEntity();
        if(!address.equals("-1")) {
            user.setAddress(address);
        }
        if(!email.equals("-1")) {
            user.setEmail(email);
        }
        if(!username.equals("-1")) {
            user.setUsername(username);
        }
//        user.setPhoto(imageBytes);
        userService.saveUpdate(user);

        return ResponseEntity.ok(mapToUserDto(user));
    }
}
