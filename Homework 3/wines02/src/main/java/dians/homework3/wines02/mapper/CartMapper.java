package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.CartDto;
import dians.homework3.wines02.model.Cart;

public class CartMapper {
    public static Cart mapToCart(CartDto cartDto) {
        return Cart.builder()
                .Id(cartDto.getId())
                .cartWines(cartDto.getCartWines())
                .updatedOn(cartDto.getUpdatedOn())
                .createdBy(cartDto.getCreatedBy())
                .createdOn(cartDto.getCreatedOn())
                .totalAmount(cartDto.getTotalAmount())
                .build();
    }

    public static CartDto mapToCartDto(Cart cart) {
        return CartDto.builder()
                .Id(cart.getId())
                .cartWines(cart.getCartWines())
                .updatedOn(cart.getUpdatedOn())
                .createdBy(cart.getCreatedBy())
                .createdOn(cart.getCreatedOn())
                .totalAmount(cart.getTotalAmount())
                .build();
    }
}
