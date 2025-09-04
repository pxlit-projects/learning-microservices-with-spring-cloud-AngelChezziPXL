package be.pxl.services.winkelwagen.service;


import be.pxl.services.winkelwagen.domain.Item;

public interface ShoppingCartService {

    void addItem(Item item);
    void removeItem(Item item);
    void saveWishList();
    void doOrder();

}
