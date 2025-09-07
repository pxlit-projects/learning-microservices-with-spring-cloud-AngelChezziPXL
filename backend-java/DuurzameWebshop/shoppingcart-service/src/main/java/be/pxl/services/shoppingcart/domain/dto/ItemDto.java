package be.pxl.services.shoppingcart.domain.dto;

import be.pxl.services.shoppingcart.domain.Item;
import be.pxl.services.shoppingcart.domain.ShoppingCart;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {

    private Long id;
    private Long productId;
    private ShoppingCartDto shoppingCartDto;
    private String name;
    private String description;
    private double price;
    private int quantity;


    public Item toItem(){
        return Item.builder()
                .id(id)
                .productId(productId)
                .shoppingCart(shoppingCartDto.toShoppingCart())
                .name(name)
                .description(description)
                .price(price)
                .quantity(quantity)
                .build();
    }

    public ItemResponse toItemResponse(){
        return ItemResponse.builder()
                .id(id)
                .productId(productId)
                .name(name)
                .description(description)
                .price(price)
                .quantity(quantity)
                .build();
    }

}
