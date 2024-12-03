package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.dto.ProductRequest;

import java.util.List;

public interface IProductService {
    void addProduct(ProductRequest productRequest);

    void updateProduct(Long id, ProductRequest productRequest);

    void updateProductCategory(Long productId, Long categoryId);
    void addTagsToProduct(Long productId, List<String> tagNames);
}
