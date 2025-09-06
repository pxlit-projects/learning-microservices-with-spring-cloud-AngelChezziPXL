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
    private Long id;
    private Long productId;
    private String name;
    private String description;
    private String categoryName;
    private Boolean available;
    private List<String> tags;
    private double price;
    private int quantity;

}
