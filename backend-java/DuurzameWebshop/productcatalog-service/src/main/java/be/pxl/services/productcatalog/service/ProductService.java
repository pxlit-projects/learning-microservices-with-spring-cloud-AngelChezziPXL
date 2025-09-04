package be.pxl.services.productcatalog.service;

import be.pxl.services.productcatalog.domain.Category;
import be.pxl.services.productcatalog.domain.Product;
import be.pxl.services.productcatalog.controller.dto.ProductRequest;
import be.pxl.services.productcatalog.controller.dto.ProductResponse;
import be.pxl.services.productcatalog.exception.ResourceNotFoundException;
import be.pxl.services.productcatalog.repository.CategoryRepository;
import be.pxl.services.productcatalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> findAll() {
        return mapProductListToProductResponseList(productRepository.findAll());
    }

    @Override
    public ProductResponse findById(Long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %s not found", id)));
        return this.mapProductToProductResponse(product);
    }

    @Override
    public void addProduct(ProductRequest productRequest) {
        productRepository.save(mapProductRequestToProduct(productRequest));
    }

    public void updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Product with id %s not found", id)));
        Product updatedProduct = mapProductRequestToProduct(productRequest);
        updatedProduct.setId(product.getId());
        productRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Product with id %s not found", id));
        }
        productRepository.deleteById(id
        );
    }

    // helper methods (CUSTOM MAPPER)
    private Product mapProductRequestToProduct(ProductRequest productRequest) {
        String categoryName = productRequest.getCategoryName().trim().toLowerCase();
        Category category = categoryRepository.findByName(categoryName).orElseThrow(() -> new ResourceNotFoundException(String.format("Category %s does not exist in the database", categoryName)));
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .category(category)
                .tags(productRequest.getTags())
                .available(productRequest.getAvailable())
                .price(productRequest.getPrice())
                .build();
    }

    private List<ProductResponse> mapProductListToProductResponseList(List<Product> productList) {
        return productList.stream().map(this::mapProductToProductResponse).toList();
    }

    private ProductResponse mapProductToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .categoryName(product.getCategory().getName())
                .tags(product.getTags())
                .available(product.isAvailable())
                .price(product.getPrice())
                .build();
    }
}