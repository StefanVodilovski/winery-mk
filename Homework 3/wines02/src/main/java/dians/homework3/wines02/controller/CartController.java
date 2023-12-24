package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.CartDto;
import dians.homework3.wines02.dto.UserDto;
import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.security.SecurityUtil;
import dians.homework3.wines02.security.UserAuthProvider;
import dians.homework3.wines02.service.CartService;
import dians.homework3.wines02.service.OrderService;
import dians.homework3.wines02.service.UserService;
import dians.homework3.wines02.service.WineService;
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
    private final CartService cartService;
    private WineService wineService;
    private final UserAuthProvider authProvider;

    public CartController(UserService userService, UserAuthProvider userAuthProvider, CartService cartService, UserAuthProvider authProvider) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
        this.cartService = cartService;
        this.authProvider = authProvider;
    }

    @Transactional
    @GetMapping("view")
    public ResponseEntity<CartDto> getCart(@RequestHeader(value = "Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Authentication authentication = userAuthProvider.validateToken(token);
        if(authentication != null && authentication.isAuthenticated()) {
            UserDto userDto = (UserDto) authentication.getPrincipal();

            UserEntity user = userService.findByUsername(userDto.getUsername());
            if (user != null) {
                user = userService.findByUsername(SecurityUtil.getSessionUser());
                return ResponseEntity.ok(mapToCartDto(user.getCart()));
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.<CartDto>status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("add/cart/item/{wineId}")
    public ResponseEntity<String> putToCart(@PathVariable("wineId") Long wineId,
                                            @RequestParam("quantity") String quantity,
                                            @RequestHeader(value = "Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Authentication authentication = authProvider.validateToken(token);
        if(authentication != null && authentication.isAuthenticated()) {
            UserDto userDto = (UserDto) authentication.getPrincipal();

            UserEntity user = userService.findByUsername(userDto.getUsername());

            if (user != null) {
                WineDto wine = wineService.findById(wineId);
                cartService.saveCart(user, wine, Integer.parseInt(quantity));
                return ResponseEntity.ok("Successfully added to cart.");
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
