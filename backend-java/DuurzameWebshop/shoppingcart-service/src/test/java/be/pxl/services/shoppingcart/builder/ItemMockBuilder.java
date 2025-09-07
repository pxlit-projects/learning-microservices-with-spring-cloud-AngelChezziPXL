package be.pxl.services.shoppingcart.builder;

import be.pxl.services.shoppingcart.domain.Item;
import be.pxl.services.shoppingcart.domain.ShoppingCart;

import java.util.Random;
import java.util.UUID;

public class ItemMockBuilder {
    private Item item;
    private ProductMockBuilder productBuilder = new ProductMockBuilder();
    private ShoppingCartMockBuilder shoppingCartMockBuilder = new ShoppingCartMockBuilder();
    private final Random RANDOM = new Random();

    public ItemMockBuilder() {
        item = Item.builder()
                .id(RANDOM.nextLong(1, 1000))
                .productId(RANDOM.nextLong(1, 1000))
                .shoppingCart(shoppingCartMockBuilder.build())
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .price(RANDOM.nextDouble(1, 500))
                .quantity(RANDOM.nextInt(1,10))
                .build();
    }

    public ItemMockBuilder withId(long id) {
        item.setId(id);
        return this;
    }
    public ItemMockBuilder withProductId(long productId) {
        item.setProductId(productId);
        return this;
    }
    public ItemMockBuilder withShoppingCart(ShoppingCart shoppingCart) {
        item.setShoppingCart(shoppingCart);
        return this;
    }
    public ItemMockBuilder withName(String name) {
        item.setName(name);
        return this;
    }
    public ItemMockBuilder withDescription(String description) {
        item.setDescription(description);
        return this;
    }
    public ItemMockBuilder withPrice(double price) {
        item.setPrice(price);
        return this;
    }
    public ItemMockBuilder withQuantity(int quantity) {
        item.setQuantity(quantity);
        return this;
    }

    public Item build() {
        return item;
    }

}
