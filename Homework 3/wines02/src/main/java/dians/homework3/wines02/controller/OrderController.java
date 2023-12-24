package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.OrderDto;
import dians.homework3.wines02.dto.UserDto;
import dians.homework3.wines02.model.AddWines;
import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.security.SecurityUtil;
import dians.homework3.wines02.security.UserAuthProvider;
import dians.homework3.wines02.service.AddWinesService;
import dians.homework3.wines02.service.CartService;
import dians.homework3.wines02.service.OrderService;
import dians.homework3.wines02.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static dians.homework3.wines02.mapper.AddWinesMapper.mapToAddWines;
import static dians.homework3.wines02.mapper.UserMapper.mapToUserDto;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/orders/")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final AddWinesService addWinesService;
    private final UserAuthProvider authProvider;


    public OrderController(OrderService orderService, UserService userService, CartService cartService, AddWinesService addWinesService, UserAuthProvider authProvider) {
        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
        this.addWinesService = addWinesService;
        this.authProvider = authProvider;
    }


    @GetMapping("all/view")
    public ResponseEntity<List<OrderDto>> getAllOrders(@RequestHeader(value = "Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Authentication authentication = authProvider.validateToken(token);
        if(authentication.isAuthenticated()) {
            String username = (String) authentication.getPrincipal();
            UserEntity user = userService.findByUsername(username);

            if (user != null) {
                return ResponseEntity.ok(orderService.findByUser(user.getId()));
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getById(orderId);
    }

    @PostMapping("order/create")
    public ResponseEntity<OrderDto> createOrder(@RequestHeader(value = "Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Authentication authentication = authProvider.validateToken(token);
        if(authentication != null && authentication.isAuthenticated()) {
            UserDto userDto = (UserDto) authentication.getPrincipal();

            UserEntity user = userService.findByUsername(userDto.getUsername());

            if (user != null) {
                Cart cart = user.getCart();
                if(cart != null) {
                        List<AddWines> addWines = cart.getCartWines().stream().map((addWine) -> mapToAddWines(addWinesService.findById(addWine.getId()))).collect(Collectors.toList());
                        Integer totalPrice = cart.getCartWines()
                                .stream()
                                .map(wine -> wine.getWine().getPrice() * wine.getQuantity())
                                .mapToInt(Integer::valueOf)
                                .sum();
                        cartService.deleteProducts(cart);
                        return ResponseEntity.ok(orderService.makeOrder(addWines, user, totalPrice));
                }
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().body(null);
    }
}
