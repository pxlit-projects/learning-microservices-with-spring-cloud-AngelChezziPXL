package be.pxl.services.logbook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductQueueMessage {
    private long userId;
    private String serviceName = "productcatalog-service";
    private ProductResponse productResponse;

    public ProductLogRequest toProductLogRequest() {            //No ID and timestamp because the entity will do it
        return ProductLogRequest.builder()
                .userId(userId)
                .productId(productResponse.getId())
                .name(productResponse.getName())
                .description(productResponse.getDescription())
                .categoryName(productResponse.getCategoryName())
                .available(productResponse.getAvailable())
                .tags(productResponse.getTags())
                .price(productResponse.getPrice())
                .build();
    }
}


