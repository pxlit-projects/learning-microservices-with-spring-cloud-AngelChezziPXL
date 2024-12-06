package be.pxl.services.productcatalogus.domain.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

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
    @NotNull
    private String categoryName;
    private Boolean available = true;
    private List<String> tags;
    @NotNull(message= "Price is required.")
    @DecimalMin(message = "Price must be greater than 0.", value = "0")
    private BigDecimal price;
}
