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
    //private ShoppingCartDto shoppingCartDto;
    private long shoppingCartId;
    private String name;
    private String description;
    private double price;
    private int quantity;

//    public Item toItem(){
//        return Item.builder()
//                .id(id)
//                .productId(productId)
//                .shoppingCart(shoppingCartDto.toShoppingCart())
//                .name(name)
//                .description(description)
//                .price(price)
//                .quantity(quantity)
//                .build();
//    }
//TODO: Delete if not needed

    public ItemResponse toItemResponse(){
        return ItemResponse.builder()
                .id(id)
                .productId(productId)
                .shoppingCartId(shoppingCartId)
                .name(name)
                .description(description)
                .price(price)
                .quantity(quantity)
                .build();
    }

}
