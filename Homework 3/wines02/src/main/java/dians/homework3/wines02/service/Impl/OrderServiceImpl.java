package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.OrderDto;
import dians.homework3.wines02.mapper.OrderMapper;
import dians.homework3.wines02.model.*;
import dians.homework3.wines02.repository.OrderRepository;
import dians.homework3.wines02.service.OrderService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static dians.homework3.wines02.mapper.OrderMapper.mapToOrderDto;
import static dians.homework3.wines02.mapper.WineryMapper.mapToWineryDto;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private static final AtomicLong counter = new AtomicLong(System.currentTimeMillis());

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void createOrder(UserEntity userEntity) {
        Cart cart = userEntity.getCart();
        Order order = new Order();
        order.setOrderWines(cart.getCartWines());
        order.setCreatedBy(userEntity);
        long uniqueNumber = counter.getAndIncrement();
        order.setCode(String.valueOf(uniqueNumber));
        order.setStatus(Status.Preparing);
        orderRepository.save(order);
    }

    @Override
    public OrderDto getById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return mapToOrderDto(order.get());
        }
        try {
            throw new NotFoundException("Order with ID " + orderId + " not found");
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
