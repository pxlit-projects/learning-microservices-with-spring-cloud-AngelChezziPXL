package be.pxl.services.winkelwagen.service;


import be.pxl.services.winkelwagen.domain.Item;

public interface IShoppingCartService {
    void addItemToCart(Long id, Item item);

}
