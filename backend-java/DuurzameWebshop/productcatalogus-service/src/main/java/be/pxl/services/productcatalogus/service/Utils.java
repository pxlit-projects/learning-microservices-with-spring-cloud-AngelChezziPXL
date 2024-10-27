package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Product;
import be.pxl.services.productcatalogus.domain.dto.ProductRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductResponse;

public class Utils {
    public ProductResponse mapProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public Product mapProductRequestToProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }
}
