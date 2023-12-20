package dians.homework3.wines02.service;


import dians.homework3.wines02.dto.CartDto;
import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.UserEntity;

public interface CartService {
    void saveCart(UserEntity user);

    Cart findById(long l);
}
