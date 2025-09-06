package be.pxl.services.productcatalog.domain.dto;

import be.pxl.services.productcatalog.domain.Product;
import jakarta.validation.constraints.*;
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

    public static ProductResponse fromProduct(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .categoryName(product.getCategory().getName())
                .tags(product.getTags())
                .available(product.isAvailable())
                .price(product.getPrice())
                .build();
    }
}
