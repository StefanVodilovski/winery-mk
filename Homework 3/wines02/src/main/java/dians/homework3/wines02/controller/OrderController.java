package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.OrderDto;
import dians.homework3.wines02.model.AddWines;
import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.security.SecurityUtil;
import dians.homework3.wines02.service.AddWinesService;
import dians.homework3.wines02.service.CartService;
import dians.homework3.wines02.service.OrderService;
import dians.homework3.wines02.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static dians.homework3.wines02.mapper.AddWinesMapper.mapToAddWines;

@CrossOrigin("http://localhost:3001")
@RestController
@RequestMapping("/orders/")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final AddWinesService addWinesService;

    public OrderController(OrderService orderService, UserService userService, CartService cartService, AddWinesService addWinesService) {
        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
        this.addWinesService = addWinesService;
    }

    public UserEntity findSessionUser() {
        return userService.findByUsername(SecurityUtil.getSessionUser());
    }

    @GetMapping("all/view")
    public List<OrderDto> getAllOrders() {
        return orderService.findByUser(findSessionUser().getId());
    }

    @GetMapping("{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getById(orderId);
    }

    @PostMapping("new")
    public ResponseEntity<String> getAllWines() {
        orderService.createOrder(findSessionUser());
        return ResponseEntity.ok("Order created");
    }

    @GetMapping("order/create")
    public ResponseEntity<OrderDto> createOrder() {
        UserEntity user = findSessionUser();
        if(user != null) {
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
        }
        return ResponseEntity.badRequest().body(null);
    }
}
