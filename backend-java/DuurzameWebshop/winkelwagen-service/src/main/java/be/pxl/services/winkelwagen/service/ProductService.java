package be.pxl.services.winkelwagen.service;

import be.pxl.services.winkelwagen.service.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    ProductDto addProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto);
    void deleteProduct(Long id);
}
