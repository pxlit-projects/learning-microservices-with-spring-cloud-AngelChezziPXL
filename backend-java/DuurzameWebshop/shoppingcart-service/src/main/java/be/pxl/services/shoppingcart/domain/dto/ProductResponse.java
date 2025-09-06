package be.pxl.services.shoppingcart.domain.dto;

import be.pxl.services.shoppingcart.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private String categoryName;
    private Boolean available;
    private List<String> tags;
    private double price;

    public Product toProduct(){
        return Product.builder()
                .id(id)
                .name(name)
                .description(description)
                .categoryName(categoryName)
                .available(available)
                .tags(tags)
                .price(price)
                .build();
    }

}
