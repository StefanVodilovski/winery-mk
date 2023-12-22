package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.OrderDto;
import dians.homework3.wines02.dto.UserDto;
import dians.homework3.wines02.model.Order;

import java.util.stream.Collectors;

public class OrderMapper {
    public static Order mapToOrder(OrderDto orderDto) {
        return Order.builder()
                .Id(orderDto.getId())
                .code(orderDto.getCode())
                .updatedOn(orderDto.getUpdatedOn())
                .status(orderDto.getStatus())
                .createdOn(orderDto.getCreatedOn())
                .total(orderDto.getTotal())
                .build();
    }

    public static OrderDto mapToOrderDto(Order order) {
        return OrderDto.builder()
                .Id(order.getId())
                .orderWines(order.getOrderWines().stream().map(AddWinesMapper::mapToAddWinesDto).collect(Collectors.toList()))
                .code(order.getCode())
                .updatedOn(order.getUpdatedOn())
                .status(order.getStatus())
                .createdOn(order.getCreatedOn())
                .createBy(UserMapper.mapToUserDto(order.getCreatedBy()))
                .total(order.getTotal())
                .build();
    }
}
