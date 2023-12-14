package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.repository.OrderRepository;
import dians.homework3.wines02.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
