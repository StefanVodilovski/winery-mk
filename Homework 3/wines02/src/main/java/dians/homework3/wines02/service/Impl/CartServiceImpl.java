package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.CartDto;
import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.repository.CartRepository;
import dians.homework3.wines02.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static dians.homework3.wines02.mapper.CartMapper.mapToCartDto;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void saveCart(UserEntity user) {
        Cart cart = new Cart();
        cart.setCreatedBy(user);
        cartRepository.save(cart);
    }

    @Override
    public Cart findById(long l) {
        Optional<Cart> cart = cartRepository.findById(l);
        if(cart.isPresent()) {
            return cart.get();
        }
        return new Cart();
    }
}
