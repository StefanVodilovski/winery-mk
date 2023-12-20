package dians.homework3.wines02.service;

import dians.homework3.wines02.dto.OrderDto;
import dians.homework3.wines02.model.UserEntity;
import org.codehaus.groovy.vmplugin.v8.PluginDefaultGroovyMethods;

import java.util.List;

public interface OrderService {
    void createOrder(UserEntity userEntity);

    OrderDto getById(Long orderId);

    List<OrderDto> findByUser(Long userId);
}
