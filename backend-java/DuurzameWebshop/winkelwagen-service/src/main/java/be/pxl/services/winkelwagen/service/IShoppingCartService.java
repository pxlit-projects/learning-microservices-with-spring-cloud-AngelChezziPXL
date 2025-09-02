package be.pxl.services.winkelwagen.service;


import be.pxl.services.winkelwagen.domain.dto.ShoppingCartItemRequest;

public interface IShoppingCartService {
    void addItem(long id, ShoppingCartItemRequest shoppingCartItemRequest);

}
