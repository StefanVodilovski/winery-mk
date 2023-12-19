package dians.homework3.wines02.service.Impl;

import dians.homework3.wines02.model.AddWines;
import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.Wine;
import dians.homework3.wines02.repository.AddWinesRepository;
import dians.homework3.wines02.service.AddWinesService;
import org.springframework.stereotype.Service;

@Service
public class AddWinesServiceImpl implements AddWinesService {
    private final AddWinesRepository addWinesRepository;

    public AddWinesServiceImpl(AddWinesRepository addWinesRepository) {
        this.addWinesRepository = addWinesRepository;
    }

    @Override
    public AddWines createAddWine(Wine wine, String quantity, Cart cart) {
        AddWines addWines = new AddWines();
        addWines.setWine(wine);
        addWines.setCart(cart);
        addWines.setQuantity(Integer.parseInt(quantity));
        addWinesRepository.save(addWines);
        return addWines;
    }
}
