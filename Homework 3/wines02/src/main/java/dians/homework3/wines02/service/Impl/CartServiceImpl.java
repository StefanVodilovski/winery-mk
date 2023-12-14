package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.repository.CartRepository;
import dians.homework3.wines02.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void saveCart(UserEntity user) {
        Cart cart = new Cart();
        cart.setCreatedBy(user);
        cartRepository.save(cart);
    }
}
