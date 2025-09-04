package be.pxl.services.winkelwagen.service.builder;

import be.pxl.services.winkelwagen.domain.Item;
import be.pxl.services.winkelwagen.domain.ShoppingCart;
import be.pxl.services.winkelwagen.domain.ShoppingCartStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShoppingCartMockBuilder {
    private ShoppingCart shoppingCart;
    private ItemMockBuilder itemMockBuilder = new ItemMockBuilder();
    private final Random random = new Random();
    private ShoppingCartStatus[] shoppingCartStatuses = ShoppingCartStatus.values();

    public ShoppingCartMockBuilder (){
        this.shoppingCart = ShoppingCart.builder()
                .id(random.nextLong(1,1000))
                .userId(random.nextInt(1, 1000))
                .status(shoppingCartStatuses[random.nextInt(shoppingCartStatuses.length)])
                .build();

        List<Item> itemList = new ArrayList<>();
        for(int i =0; i < 5; i++){
            itemList.add(itemMockBuilder.build());
        }

        shoppingCart.setItems(itemList);
    }

    public ShoppingCartMockBuilder withId(Long id){
        shoppingCart.setId(id);
        return this;
    }

    public ShoppingCartMockBuilder withUserId(Long userId){
        shoppingCart.setUserId(userId);
        return this;
    }

    public ShoppingCartMockBuilder withCartItems(List<Item> items){
        this.shoppingCart.setItems(items);
        return this;
    }

    public ShoppingCart build(){
        return shoppingCart;
    }
}
