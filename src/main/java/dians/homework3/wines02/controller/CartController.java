package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.CartDto;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.service.CartService;
import dians.homework3.wines02.service.OrderService;
import dians.homework3.wines02.service.UserService;
import org.springframework.web.bind.annotation.*;


import static dians.homework3.wines02.mapper.CartMapper.mapToCartDto;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/cart/")
public class CartController {
    private final UserService userService;
    private final CartService cartService;

    public CartController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("{userId}")
    public CartDto getCart(@PathVariable("userId") Long userId) {
        return mapToCartDto(userService.findById(userId).getCart());
    }
}
