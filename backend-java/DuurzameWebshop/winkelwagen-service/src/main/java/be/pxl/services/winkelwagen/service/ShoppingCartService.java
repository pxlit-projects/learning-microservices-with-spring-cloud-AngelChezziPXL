package be.pxl.services.winkelwagen.service;


public interface ShoppingCartService {

    void addItem(long id);
    void removeItem(long id);
    void saveWishList();
    void doOrder();

}
