package be.pxl.services.logbook.builder;

import be.pxl.services.logbook.domain.ProductLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ProductLogBuilder {

    private ProductLog productLog;
    private final Random RANDOM = new Random();


    public ProductLogBuilder() {
        List<String> tags = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tags.add(UUID.randomUUID().toString());
        }
        productLog = ProductLog.builder()
                .id(RANDOM.nextLong(1L, 1000L))
                .productId(RANDOM.nextLong(1L, 1000L))
                .senderId(RANDOM.nextLong(1L, 1000L))
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .categoryName(UUID.randomUUID().toString())
                .tags(tags)
                .available(RANDOM.nextBoolean())
                .price(RANDOM.nextDouble())
                .build();
    }

    // Static builder entry point
    public static ProductLogBuilder builder() {
        return new ProductLogBuilder();
    }

    public ProductLogBuilder withId(Long id) {
        productLog.setId(id);
        return this;
    }

    public ProductLogBuilder withProductId(Long productId) {
        productLog.setProductId(productId);
        return this;
    }

    public ProductLogBuilder withSenderId(Long senderId) {
        productLog.setSenderId(senderId);
        return this;
    }

    public ProductLogBuilder withName(String name) {
        productLog.setName(name);
        return this;
    }

    public ProductLogBuilder withDescription(String description) {
        productLog.setDescription(description);
        return this;
    }

    public ProductLogBuilder withCategoryName(String categoryName) {
        productLog.setCategoryName(categoryName);
        return this;
    }

    public ProductLogBuilder withTags(List<String> tags) {
        productLog.setTags(tags);
        return this;
    }

    public ProductLogBuilder withAvailable(boolean available) {
        productLog.setAvailable(available);
        return this;
    }

    public ProductLogBuilder withPrice(double price) {
        productLog.setPrice(price);
        return this;
    }

    public ProductLog build() {
        return productLog;
    }





}
