package be.pxl.services.logbook.domain;

import be.pxl.services.logbook.domain.dto.ProductLogRequest;
import be.pxl.services.logbook.domain.dto.ProductLogResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLog {
    @Id
    @GeneratedValue
    private Long id;
    private Long senderId;
    private Long productId;
    private String name;
    private String description;
    private String categoryName;
    private Boolean available;
    private List<String> tags;
    private double price;

    public ProductLogResponse toProductLogResponse() {
        return ProductLogResponse.builder()
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

    public ProductLogRequest toProductLogRequest() {
        return ProductLogRequest.builder()
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
