package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.CartDto;
import dians.homework3.wines02.model.Cart;

public class CartMapper {
    public static Cart mapToCart(CartDto cartDto) {
        return Cart.builder()
                .cartWines(cartDto.getCartWines())
                .updatedOn(cartDto.getUpdatedOn())
                .createdBy(cartDto.getCreatedBy())
                .createdOn(cartDto.getCreatedOn())
                .deliveryPrice(cartDto.getDeliveryPrice())
                .totalAmount(cartDto.getTotalAmount())
                .build();
    }

    public static CartDto mapToCartDto(Cart cart) {
        return CartDto.builder()
                .cartWines(cart.getCartWines())
                .updatedOn(cart.getUpdatedOn())
                .createdBy(cart.getCreatedBy())
                .createdOn(cart.getCreatedOn())
                .deliveryPrice(cart.getDeliveryPrice())
                .totalAmount(cart.getTotalAmount())
                .build();
    }
}
