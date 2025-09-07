package be.pxl.services.shoppingcart.domain;

import be.pxl.services.shoppingcart.domain.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String categoryName;
    private Boolean available;
    private List<String> tags;
    private double price;

    public ProductDto toProductDto() {
        return ProductDto.builder()
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
