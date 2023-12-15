package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.OrderDto;
import dians.homework3.wines02.model.Order;

public class OrderMapper {
    public static Order mapToOrder(OrderDto orderDto) {
        return Order.builder()
                .Id(orderDto.getId())
                .orderWines(orderDto.getOrderWines())
                .code(orderDto.getCode())
                .updatedOn(orderDto.getUpdatedOn())
                .createdBy(orderDto.getCreatedBy())
                .status(orderDto.getStatus())
                .createdOn(orderDto.getCreatedOn())
                .build();
    }

    public static OrderDto mapToOrderDto(Order order) {
        return OrderDto.builder()
                .Id(order.getId())
                .orderWines(order.getOrderWines())
                .code(order.getCode())
                .updatedOn(order.getUpdatedOn())
                .createdBy(order.getCreatedBy())
                .status(order.getStatus())
                .createdOn(order.getCreatedOn())
                .build();
    }
}
