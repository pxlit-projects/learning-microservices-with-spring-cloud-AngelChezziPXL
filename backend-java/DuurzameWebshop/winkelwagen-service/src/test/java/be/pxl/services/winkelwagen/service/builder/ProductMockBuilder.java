package be.pxl.services.winkelwagen.service.builder;

import be.pxl.services.winkelwagen.domain.Product;

import java.util.Random;
import java.util.UUID;

public class ProductMockBuilder {
    private Random random = new Random();
    private Product product;

    public ProductMockBuilder(){
        this.product = Product.builder()
                .Id(random.nextLong(1L, 1000L ))
                .sellerProductId(random.nextLong(1L, 1000L))
                .name(UUID.randomUUID().toString())
                .price(random.nextDouble(1.0,1000.0))
                .description(UUID.randomUUID().toString())
                .build();
    }

    public ProductMockBuilder withId(Long id) {
        product.setId(id);
        return this;
    }

    public ProductMockBuilder withSellerProductId(Long sellerProductId) {
        product.setSellerProductId(sellerProductId);
        return this;
    }
    public ProductMockBuilder withPrice(double price) {
        product.setPrice(price);
        return this;
    }

    public ProductMockBuilder withName(String name){
        product.setName(name);
        return this;
    }

    public ProductMockBuilder withDescription(String description){
        product.setDescription(description);
        return this;
    }
    public Product makeDuplicateProductObject(Product product){
        Product duplicateProduct = new Product();
        duplicateProduct.setId(product.getId());
        duplicateProduct.setSellerProductId(product.getSellerProductId());
        duplicateProduct.setName(product.getName());
        duplicateProduct.setDescription(product.getDescription());
        duplicateProduct.setPrice(product.getPrice());
        duplicateProduct.setSellerProductId(product.getSellerProductId());
        return duplicateProduct;
    }
    public Product build(){
        return this.product;
    }
}
