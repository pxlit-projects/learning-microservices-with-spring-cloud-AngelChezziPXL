package be.pxl.services.shoppingcart.domain.factory;

import be.pxl.services.shoppingcart.domain.Item;
import be.pxl.services.shoppingcart.domain.ShoppingCart;
import be.pxl.services.shoppingcart.exception.ResourceNotFoundException;
import be.pxl.services.shoppingcart.repository.IShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemFactory implements IItemFactory {
    private final IShoppingCartRepository shoppingCartRepository;


    @Override
    public Item createItem(long userId, long productId, ShoppingCart shoppingCart, String name, String description, double price, int quantity) {
        return Item.builder()
                .productId(productId)
                .shoppingCart(shoppingCart)
                .name(name)
                .description(description)
                .price(price)
                .quantity(quantity)
                .build();
    }
}
