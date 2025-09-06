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
    private ProductDto productDto;

    public ProductLogRequest toProductLogRequest() {            //No ID and timestamp because the entity will do it
        return ProductLogRequest.builder()
                .userId(userId)
                .productId(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .categoryName(productDto.getCategoryName())
                .available(productDto.getAvailable())
                .tags(productDto.getTags())
                .price(productDto.getPrice())
                .build();
    }
}


