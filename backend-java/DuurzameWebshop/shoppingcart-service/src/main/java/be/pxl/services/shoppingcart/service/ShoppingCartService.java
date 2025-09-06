package be.pxl.services.shoppingcart.service;

import be.pxl.services.shoppingcart.repository.IShoppingCartRepository;
import be.pxl.services.shoppingcart.domain.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService implements IShoppingCartService {
    @Autowired
    private IShoppingCartRepository IShoppingCartRepository;


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
