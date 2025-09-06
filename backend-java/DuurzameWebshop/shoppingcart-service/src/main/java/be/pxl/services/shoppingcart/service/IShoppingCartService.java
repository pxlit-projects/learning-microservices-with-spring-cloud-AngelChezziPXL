package be.pxl.services.shoppingcart.service;


import be.pxl.services.shoppingcart.domain.dto.ItemResponse;
import be.pxl.services.shoppingcart.domain.dto.ItemDto;

import java.util.List;

public interface IShoppingCartService {

    void addItemToShoppingCart(ItemDto itemDto);
    void removeItemById(Long id);
    void saveWishList();
    void doOrder();

    List<ItemResponse> getAllItemsWithProductDetails();
}
