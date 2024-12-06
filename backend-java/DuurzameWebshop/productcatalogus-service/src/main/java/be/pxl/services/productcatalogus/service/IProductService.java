package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Product;
import be.pxl.services.productcatalogus.domain.dto.CategoryRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductResponse;

import java.util.List;

public interface IProductService {
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);


    void addProduct(ProductRequest productRequest);

    void updateProduct(Long id, ProductRequest productRequest);
    void setCategory(Long id, CategoryRequest categoryRequest);

    void updateProductCategory(Long productId, Long categoryId);
    void addTagsToProduct(Long productId, List<String> tagNames);
}
