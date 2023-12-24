package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.dto.CartDto;
import dians.homework3.wines02.dto.WineDto;
import dians.homework3.wines02.model.AddWines;
import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.UserEntity;
import dians.homework3.wines02.repository.AddWinesRepository;
import dians.homework3.wines02.repository.CartRepository;
import dians.homework3.wines02.service.CartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dians.homework3.wines02.mapper.CartMapper.mapToCartDto;
import static dians.homework3.wines02.mapper.WineMapper.mapToWine;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final AddWinesRepository addWinesRepository;

    public CartServiceImpl(CartRepository cartRepository, AddWinesRepository addWinesRepository) {
        this.cartRepository = cartRepository;
        this.addWinesRepository = addWinesRepository;
    }

    @Override
    public void saveCart(UserEntity user, WineDto wineDto, Integer quantity) {
        Cart cart = user.getCart();
        List<AddWines> wines = cart.getCartWines();
        AddWines hasAddProduct = wines.stream().filter((wine) -> wine.getWine().getId().equals(wineDto.getId())).findFirst().orElse(null);
        if(hasAddProduct != null) {
            hasAddProduct.setQuantity(hasAddProduct.getQuantity()+quantity);
            addWinesRepository.save(hasAddProduct);
        } else {
            AddWines addWines = new AddWines();
            addWines.setWine(mapToWine(wineDto));
            addWines.setQuantity(quantity);
            addWines.setCart(cart);
            addWinesRepository.save(addWines);
            wines.add(addWines);
            cart.setCartWines(wines);
        }
        cartRepository.save(cart);
    }

    @Override
    public void save(UserEntity user) {
        Cart cart = new Cart();
        cart.setCreatedBy(user);
        cartRepository.save(cart);
    }

    @Override
    public void deleteAddWines(Cart cart) {
        cart.setCartWines(new ArrayList<>());
        cartRepository.save(cart);
    }

    @Override
    public Cart findById(long l) {
        Optional<Cart> cart = cartRepository.findById(l);
        return cart.orElseGet(Cart::new);
    }
}
