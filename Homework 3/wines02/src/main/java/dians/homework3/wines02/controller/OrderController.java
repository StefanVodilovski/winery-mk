package dians.homework3.wines02.controller;

import dians.homework3.wines02.dto.OrderDto;
import dians.homework3.wines02.mapper.OrderMapper;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.service.OrderService;
import dians.homework3.wines02.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders/")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    private UserEntity getUser(Long userId) {
        return userService.findById(userId);
    }

    @GetMapping("all/{userId}")
    public List<OrderDto> getAllOrders(@PathVariable("userId") Long userId) {
        return orderService.findByUser(userId);
    }

    @GetMapping("{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getById(orderId);
    }

    @PostMapping("new/{userId}")
    public ResponseEntity<String> getAllWines(@PathVariable("userId") Long userId) {
        UserEntity userEntity = getUser(userId);
        orderService.createOrder(userEntity);
        return ResponseEntity.ok("Order created");
    }
}
