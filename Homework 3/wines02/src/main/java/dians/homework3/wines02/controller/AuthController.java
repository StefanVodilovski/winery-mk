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
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import io.jsonwebtoken.Claims;

import static dians.homework3.wines02.mapper.UserMapper.mapToUserDto;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final CartService cartService;
    private final UserAuthProvider userAuthProvider;
    private final UserAuthProvider authProvider;

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

    @GetMapping("logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext(); // Clear the security context
        return ResponseEntity.ok("Logout successful");
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
    private ResponseEntity<UserDto> editUser(@RequestHeader(value = "Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Authentication authentication = authProvider.validateToken(token);
        if(authentication != null && authentication.isAuthenticated()) {
            UserDto userDto = (UserDto) authentication.getPrincipal();

                if (userDto != null) {
                    return ResponseEntity.ok(userDto);
                } else {
                    return ResponseEntity.notFound().build();
                }
        }
        return ResponseEntity.<UserDto>status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("user/edit")
    private ResponseEntity<UserDto> updateEditUser(@RequestParam String username,
                                @RequestParam String email,
                                @RequestParam MultipartFile profileImage,
                                @RequestParam String address,
                                                   @RequestHeader(value = "Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Authentication authentication = authProvider.validateToken(token);
        if(authentication != null && authentication.isAuthenticated()) {
            UserDto userDto = (UserDto) authentication.getPrincipal();

            UserEntity user = userService.findByUsername(userDto.getUsername());

            if (user != null) {
                if(!address.equals("-1")) {
                    user.setAddress(address);
                }
                if(!email.equals("-1")) {
                    user.setEmail(email);
                }
                if(!username.equals("-1")) {
                    user.setUsername(username);
                }
                userService.saveUpdate(user);
                UserDto userDto2 = mapToUserDto(user);
                return ResponseEntity.ok(userDto2);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.<UserDto>status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
