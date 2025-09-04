package be.pxl.services.winkelwagen.service;

import be.pxl.services.winkelwagen.domain.Item;
import be.pxl.services.winkelwagen.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;


    @Override
    public void addItem(Item item) {

    }

    @Override
    public void removeItem(Item item) {

    }

    @Override
    public void saveWishList() {

    }

    @Override
    public void doOrder() {

    }
}
