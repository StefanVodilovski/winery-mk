package dians.homework3.wines02.service;

import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.Wine;

public interface AddWinesService {
    void createAddWine(Wine wine, String quantity, Cart cart);
}
