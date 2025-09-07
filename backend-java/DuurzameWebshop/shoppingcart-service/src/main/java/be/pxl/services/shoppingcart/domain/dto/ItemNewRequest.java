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
    @Positive
    private long shoppingCartId;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @Positive
    private double price;
    @Positive
    private int quantity;

}
