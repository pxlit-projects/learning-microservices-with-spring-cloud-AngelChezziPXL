package be.pxl.services.productcatalog.service;

import be.pxl.services.productcatalog.domain.dto.ProductRequest;
import be.pxl.services.productcatalog.domain.dto.ProductResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IProductService {
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);
    ProductResponse addProduct(long userId, ProductRequest productRequest) throws JsonProcessingException;
    ProductResponse updateProduct(long userId, long id, ProductRequest productRequest) throws JsonProcessingException;
    void deleteProduct(long userId, long id) throws JsonProcessingException;
}
