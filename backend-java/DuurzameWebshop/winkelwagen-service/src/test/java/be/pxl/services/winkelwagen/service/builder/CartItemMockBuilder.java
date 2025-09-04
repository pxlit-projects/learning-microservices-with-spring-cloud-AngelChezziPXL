package be.pxl.services.winkelwagen.service.builder;

import be.pxl.services.winkelwagen.domain.CartItem;
import be.pxl.services.winkelwagen.domain.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartItemMockBuilder {
    private CartItem cartItem;
    private ProductMockBuilder productBuilder = new ProductMockBuilder();
    private ShoppingCartMockBuilder shoppingCartMockBuilder = new ShoppingCartMockBuilder();
    private Random random = new Random();

    public CartItemMockBuilder() {
        cartItem = new CartItem();
        cartItem.setId(random.nextLong(1,1000));

        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            ShoppingCart shoppingCart = shoppingCartMockBuilder.build();
            shoppingCarts.add(shoppingCart);
        }

        cartItem.setProduct(productBuilder.build());
        cartItem.setQuantity(random.nextInt(1,25));
    }

    public CartItem build() {
        return cartItem;
    }



}
