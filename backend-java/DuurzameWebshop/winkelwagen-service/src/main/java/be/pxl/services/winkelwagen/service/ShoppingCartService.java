package be.pxl.services.winkelwagen.service;


import be.pxl.services.winkelwagen.service.dto.ItemDto;

public interface ShoppingCartService {

    void addItemToShoppingCart(ItemDto itemDto);
    void removeItemById(Long id);
    void saveWishList();
    void doOrder();

}
