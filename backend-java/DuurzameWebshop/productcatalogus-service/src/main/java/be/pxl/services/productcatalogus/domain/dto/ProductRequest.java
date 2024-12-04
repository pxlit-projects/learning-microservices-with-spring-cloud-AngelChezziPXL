package be.pxl.services.productcatalogus.domain.dto;

import be.pxl.services.productcatalogus.domain.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    //private Long id;
    @NotNull(message= "Product name cannot be empty.")
    private String name;
    @NotNull(message= "Product name cannot be empty.")
    private String description;
    private String category;
    private String tags;
    @NotNull(message= "Price is required.")
    @DecimalMin(message = "Price must be greater than 0.", value = "0")
    private BigDecimal price;
}
