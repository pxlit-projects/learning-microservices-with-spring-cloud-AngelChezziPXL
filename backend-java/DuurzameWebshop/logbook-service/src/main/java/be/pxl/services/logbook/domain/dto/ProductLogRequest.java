package be.pxl.services.logbook.domain.dto;

import be.pxl.services.logbook.domain.ProductLog;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLogRequest {
    @NotNull
    private long id;
    @NotNull
    private long senderId;
    @NotNull
    private long productId;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private String categoryName;
    private boolean available;
    @NotEmpty
    private List<String> tags;
    private double price;

    public ProductLog toProductLog() {
        return ProductLog.builder()
                .id(id)
                .senderId(senderId)
                .productId(productId)
                .name(name)
                .description(description)
                .categoryName(categoryName)
                .available(available)
                .tags(tags)
                .price(price)
                .build();
    }
}
