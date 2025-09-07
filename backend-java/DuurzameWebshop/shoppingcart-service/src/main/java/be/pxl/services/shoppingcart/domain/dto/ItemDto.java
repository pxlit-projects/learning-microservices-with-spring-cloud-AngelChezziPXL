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
    private ProductDto productDto;
    private int quantity;
    ShoppingCartDto shoppingCartDto;


    public Item toItem(){
        return Item.builder()
                .id(id)
                .productId(productId)
                .product(productDto.toProduct())
                .quantity(quantity)
                .shoppingCart(shoppingCartDto.toShoppingCart())
                .build();
    }


}
