package be.pxl.services.shoppingcart.domain.factory;

import be.pxl.services.shoppingcart.domain.Item;
import be.pxl.services.shoppingcart.domain.ShoppingCart;

public interface IItemFactory {
    Item createItem(long userId, long productId, ShoppingCart shoppingCart, String name, String description, double price, int quantity );
}
