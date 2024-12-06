package be.pxl.services.productcatalogus.domain.dto;

import be.pxl.services.productcatalogus.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private CategoryRecord categoryRecord;
    private Boolean available;
    private List<TagRecord> tags = new ArrayList<>();
    private BigDecimal price;
}
