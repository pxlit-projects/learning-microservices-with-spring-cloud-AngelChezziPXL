package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.dto.ProductRequest;

public interface IProductService {
    void addProduct(ProductRequest productRequest);

    void updateProduct(Long id, ProductRequest productRequest);
}
