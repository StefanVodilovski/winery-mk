package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.CartDto;
import dians.homework3.wines02.model.Cart;

import java.util.stream.Collectors;

public class CartMapper {
    public static Cart mapToCart(CartDto cartDto) {
        return Cart.builder()
                .Id(cartDto.getId())
//                .cartWines(cartDto.getCartWines())
                .updatedOn(cartDto.getUpdatedOn())
//                .createdBy(cartDto.getCreatedBy())
                .createdOn(cartDto.getCreatedOn())
                .build();
    }

    public static CartDto mapToCartDto(Cart cart) {
        return CartDto.builder()
                .Id(cart.getId())
                .cartWines(cart.getCartWines().stream().map(AddWinesMapper::mapToAddWinesDto).collect(Collectors.toList()))
                .updatedOn(cart.getUpdatedOn())
                .createdBy(UserMapper.mapToUserDto(cart.getCreatedBy()))
                .createdOn(cart.getCreatedOn())
                .build();
    }
}
