package be.pxl.services.winkelwagen.service;

import be.pxl.services.winkelwagen.domain.CartItem;
import be.pxl.services.winkelwagen.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;


    @Override
    public void addItem(CartItem cartItem) {

    }

    @Override
    public void removeItem(CartItem cartItem) {

    }

    @Override
    public void saveWishList() {

    }

    @Override
    public void doOrder() {

    }
}
