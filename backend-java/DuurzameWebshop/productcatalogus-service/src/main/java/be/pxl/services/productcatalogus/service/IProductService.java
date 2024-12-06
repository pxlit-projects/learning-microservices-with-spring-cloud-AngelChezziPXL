package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.dto.ProductRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductResponse;

import java.util.List;

public interface IProductService {
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);
    void addProduct(ProductRequest productRequest);
    void updateProduct(Long id, ProductRequest productRequest);
    void deleteProduct(Long id);
}
