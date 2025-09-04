package be.pxl.services.winkelwagen.service;


import be.pxl.services.winkelwagen.domain.CartItem;

public interface ShoppingCartService {

    void addItem(CartItem cartItem);
    void removeItem(CartItem cartItem);
    void saveWishList();
    void doOrder();

}
