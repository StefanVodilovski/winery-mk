package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.CartDto;
import dians.homework3.wines02.dto.UserDto;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.security.SecurityUtil;
import dians.homework3.wines02.security.UserAuthProvider;
import dians.homework3.wines02.service.CartService;
import dians.homework3.wines02.service.OrderService;
import dians.homework3.wines02.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;

import static dians.homework3.wines02.mapper.CartMapper.mapToCartDto;
import static dians.homework3.wines02.mapper.UserMapper.mapToUserDto;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/cart/")
public class CartController {
    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    public CartController(UserService userService, UserAuthProvider userAuthProvider) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
    }

    @Transactional
    @GetMapping("view")
    public ResponseEntity<CartDto> getCart(@RequestHeader(value = "Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Authentication authentication = userAuthProvider.validateToken(token);
        if(authentication.isAuthenticated()) {
            String username = (String) authentication.getPrincipal();
            UserEntity user = userService.findByUsername(username);
            if (user != null) {
                user = userService.findByUsername(SecurityUtil.getSessionUser());
                return ResponseEntity.ok(mapToCartDto(user.getCart()));
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.<CartDto>status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
