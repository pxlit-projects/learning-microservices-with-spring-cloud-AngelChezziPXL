package be.pxl.services.shoppingcart.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {
    private long id;
    private long productId;
    private long shoppingCartId;
    private String name;
    private String description;
    private double price;
    private int quantity;

}
