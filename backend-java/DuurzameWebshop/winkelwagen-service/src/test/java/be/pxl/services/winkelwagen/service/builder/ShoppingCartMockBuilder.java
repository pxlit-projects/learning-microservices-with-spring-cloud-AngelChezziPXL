package be.pxl.services.winkelwagen.service.builder;

import be.pxl.services.winkelwagen.domain.CartItem;
import be.pxl.services.winkelwagen.domain.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShoppingCartMockBuilder {
    private ShoppingCart shoppingCart;
    private CartItemMockBuilder cartItemMockBuilder = new CartItemMockBuilder();
    private Random random = new Random();

    public ShoppingCartMockBuilder (){
        this.shoppingCart = ShoppingCart.builder()
                .id(random.nextLong(1,1000))
                .userId(random.nextInt(1, 1000))
                .build();

        List<CartItem> cartItemList = new ArrayList<>();
        for(int i =0; i < 5; i++){
            cartItemList.add(cartItemMockBuilder.build());
        }

        shoppingCart.setCartItems(cartItemList);
    }

    public ShoppingCartMockBuilder withId(Long id){
        shoppingCart.setId(id);
        return this;
    }

    public ShoppingCartMockBuilder withUserId(Long userId){
        shoppingCart.setUserId(userId);
        return this;
    }

    public ShoppingCartMockBuilder withCartItems(List<CartItem> cartItems){
        this.shoppingCart.setCartItems(cartItems);
        return this;
    }

    public ShoppingCart build(){
        return shoppingCart;
    }
}
