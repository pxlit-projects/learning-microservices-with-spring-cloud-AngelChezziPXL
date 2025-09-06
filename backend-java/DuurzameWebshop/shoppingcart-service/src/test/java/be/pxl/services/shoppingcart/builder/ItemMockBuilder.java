package be.pxl.services.shoppingcart.builder;

import be.pxl.services.shoppingcart.domain.Item;
import be.pxl.services.shoppingcart.domain.Product;
import be.pxl.services.shoppingcart.domain.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemMockBuilder {
    private Item item;
    private ProductMockBuilder productBuilder = new ProductMockBuilder();
    private ShoppingCartMockBuilder shoppingCartMockBuilder = new ShoppingCartMockBuilder();
    private final Random RANDOM = new Random();

    public ItemMockBuilder() {
        item = new Item();
        Product product = productBuilder.build();

        item.setId(RANDOM.nextLong(1,1000));
        item.setProductId(product.getId());
        item.setProduct(product);
        item.setQuantity(RANDOM.nextInt(1,25));
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            ShoppingCart shoppingCart = shoppingCartMockBuilder.build();
            shoppingCarts.add(shoppingCart);
        }

    }

    public Item build() {
        return item;
    }



}
