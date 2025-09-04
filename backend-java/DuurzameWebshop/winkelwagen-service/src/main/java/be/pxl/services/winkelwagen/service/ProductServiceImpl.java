package be.pxl.services.winkelwagen.service;

import be.pxl.services.winkelwagen.controller.dto.ProductDto;
import be.pxl.services.winkelwagen.domain.Product;
import be.pxl.services.winkelwagen.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getAll() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        if (products.size() != 0) {
            for (Product product : products) {
                productDtos.add(ProductDto.fromProduct(product));
            }
        }
        return productDtos;

    }
}
