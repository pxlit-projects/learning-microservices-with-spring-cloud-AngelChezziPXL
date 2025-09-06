package be.pxl.services.productcatalog.builders;

import be.pxl.services.productcatalog.domain.dto.ProductQueueMessage;
import be.pxl.services.productcatalog.domain.dto.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Random;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class ProductQueueMessageBuilder {
    private ProductQueueMessage productQueueMessage;
    private Random random = new Random();
    private ProductBuilder productBuilder = new ProductBuilder();

    public ProductQueueMessageBuilder() {
        productQueueMessage = ProductQueueMessage.builder()
                .userId(random.nextLong(1L, 100L))
                .serviceName(UUID.randomUUID().toString())
                .productResponse(productBuilder.build().toProductResponse())
                .build();
    }

    public ProductQueueMessageBuilder withUserId(Long userId) {
        productQueueMessage.setUserId(userId);
        return this;
    }

    public ProductQueueMessageBuilder withServiceName(String serviceName) {
        productQueueMessage.setServiceName(serviceName);
        return this;
    }

    public ProductQueueMessageBuilder withProductResponse(ProductResponse productResponse) {
        productQueueMessage.setProductResponse(productResponse);
        return this;
    }

    public ProductQueueMessage build() {
        return productQueueMessage;
    }

}
