package be.pxl.services.productcatalog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductQueueMessage {
    private long userId;
    private String serviceName = "productcatalog-service";
    private String timestamp = LocalDateTime.now().toString();
    private ProductResponse productResponse;
}
