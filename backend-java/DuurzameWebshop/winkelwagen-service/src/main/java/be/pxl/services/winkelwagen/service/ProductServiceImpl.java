package be.pxl.services.winkelwagen.service;

import be.pxl.services.winkelwagen.service.dto.ProductDto;
import be.pxl.services.winkelwagen.domain.Product;
import be.pxl.services.winkelwagen.repository.ProductRepository;
import be.pxl.services.winkelwagen.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getAll() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        if (!products.isEmpty()) {
            for (Product product : products) {
                productDtos.add(ProductDto.fromProduct(product));
            }
        }
        return productDtos;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = ProductDto.toProduct(productDto);
        var result = productRepository.save(product);
        productDto.setId(result.getId());
        return productDto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product with id " + productDto.getId() + " not found"));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        var result = productRepository.save(product);
        return ProductDto.fromProduct(result);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        productRepository.deleteById(product.getId());
    }
}
