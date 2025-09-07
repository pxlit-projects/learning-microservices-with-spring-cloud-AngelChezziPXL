package be.pxl.services.shoppingcart.domain.dto;

import be.pxl.services.shoppingcart.domain.Item;
import be.pxl.services.shoppingcart.domain.Product;
import be.pxl.services.shoppingcart.domain.ShoppingCart;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemNewRequest {
    @Positive
    private long productId;
    @NotNull
    private Product product;
    @NotNull
    private ShoppingCart shoppingCart;
    @Positive
    private int quantity;

    public Item toItem(){
        return Item.builder()
                .id(null)
                .productId(productId)
                .product(product)
                .shoppingCart(shoppingCart)
                .quantity(quantity)
                .build();
    }
}
