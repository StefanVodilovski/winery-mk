package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.RegistrationDto;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.security.SecurityUtil;
import dians.homework3.wines02.service.CartService;
import dians.homework3.wines02.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {
    private final UserService userService;
    private final CartService cartService;

    public AuthController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostMapping("/register/save")
    public ResponseEntity<String> registerUser(@RequestParam String email,
                                               @RequestParam String password,
                                               @RequestParam String address,
                                               @RequestParam String username,
                                               @RequestParam String phoneNumber) {
        UserEntity existedUserEmail = userService.findByEmail(email);
        if(existedUserEmail != null && existedUserEmail.getEmail() != null && !existedUserEmail.getEmail().isEmpty()) {
            ResponseEntity.ok("Registration failed");
        }

        UserEntity existedUserUsername = userService.findByUsername(username);
        if(existedUserUsername != null && existedUserUsername.getUsername() != null && !existedUserUsername.getUsername().isEmpty()) {
            ResponseEntity.ok("Registration failed");
        }
        RegistrationDto user = new RegistrationDto();
        user.setAddress(address);
        user.setEmail(email);
        user.setUsername(username);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        UserEntity userEntity = userService.saveUser(user);
        cartService.saveCart(userEntity);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/user/{userId}/edit")
    private String editUser(@PathVariable("userId") Long userId, Model model) {
        UserEntity user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user_edit";
    }

//    @PostMapping("/user/{userId}/edit")
//    private String updateEditUser(@PathVariable("userId") Long userId, @ModelAttribute("user") RegistrationDto user) {
//        user.setId(userId);
//        userService.save(user);
//        return "redirect:/user/manager?success";
//    }

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userService.findByEmail(email);
        model.addAttribute("user", user);
        return "profile";
    }
}
