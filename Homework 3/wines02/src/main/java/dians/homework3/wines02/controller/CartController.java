package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.CartDto;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.security.SecurityUtil;
import dians.homework3.wines02.service.CartService;
import dians.homework3.wines02.service.OrderService;
import dians.homework3.wines02.service.UserService;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;

import static dians.homework3.wines02.mapper.CartMapper.mapToCartDto;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/cart/")
public class CartController {
    private final UserService userService;

    public CartController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @GetMapping("view")
    public CartDto getCart() {
        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
        return mapToCartDto(user.getCart());
    }
}
