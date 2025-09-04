package be.pxl.services.shoppingcart.service;

import be.pxl.services.shoppingcart.repository.ShoppingCartRepository;
import be.pxl.services.shoppingcart.service.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;


    @Override
    public void addItemToShoppingCart(ItemDto itemDto) {

    }

    @Override
    public void removeItemById(Long id) {

    }

    @Override
    public void saveWishList() {

    }

    @Override
    public void doOrder() {

    }
}
