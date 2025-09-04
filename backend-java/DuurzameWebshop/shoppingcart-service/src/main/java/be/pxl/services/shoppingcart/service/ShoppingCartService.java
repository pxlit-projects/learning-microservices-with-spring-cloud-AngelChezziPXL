package be.pxl.services.shoppingcart.service;


import be.pxl.services.shoppingcart.service.dto.ItemDto;

public interface ShoppingCartService {

    void addItemToShoppingCart(ItemDto itemDto);
    void removeItemById(Long id);
    void saveWishList();
    void doOrder();

}
