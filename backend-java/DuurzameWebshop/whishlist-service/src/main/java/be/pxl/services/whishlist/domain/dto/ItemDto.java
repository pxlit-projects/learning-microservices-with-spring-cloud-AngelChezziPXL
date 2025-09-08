package be.pxl.services.whishlist.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
