package be.pxl.services.winkelwagen.service.builder;

import be.pxl.services.winkelwagen.domain.Item;
import be.pxl.services.winkelwagen.domain.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemMockBuilder {
    private Item item;
    private ProductMockBuilder productBuilder = new ProductMockBuilder();
    private ShoppingCartMockBuilder shoppingCartMockBuilder = new ShoppingCartMockBuilder();
    private Random random = new Random();

    public ItemMockBuilder() {
        item = new Item();
        item.setId(random.nextLong(1,1000));

        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            ShoppingCart shoppingCart = shoppingCartMockBuilder.build();
            shoppingCarts.add(shoppingCart);
        }

        item.setProduct(productBuilder.build());
        item.setQuantity(random.nextInt(1,25));
    }

    public Item build() {
        return item;
    }



}
