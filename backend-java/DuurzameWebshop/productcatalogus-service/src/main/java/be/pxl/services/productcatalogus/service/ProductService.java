package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.Product;
import be.pxl.services.productcatalogus.controller.dto.ProductRequest;
import be.pxl.services.productcatalogus.controller.dto.ProductResponse;
import be.pxl.services.productcatalogus.exception.ResourceNotFoundExeception;
import be.pxl.services.productcatalogus.repository.CategoryRepository;
import be.pxl.services.productcatalogus.repository.ProductRepository;
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
                .orElseThrow(() -> new ResourceNotFoundExeception(String.format("Product with id %s not found", id)));
        return this.mapProductToProductResponse(product);
    }

    @Override
    public void addProduct(ProductRequest productRequest) {
        productRepository.save(mapProductRequestToProduct(productRequest));
    }

    public void updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeception(String.format("Product with id %s not found", id)));
        Product updatedProduct = mapProductRequestToProduct(productRequest);
        updatedProduct.setId(product.getId());
        productRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // helper methods (CUSTOM MAPPER)
    private Product mapProductRequestToProduct(ProductRequest productRequest) {
        String categoryName = productRequest.getCategoryName().trim().toLowerCase();
        Category category = categoryRepository.findByName(categoryName).orElseThrow(() -> new ResourceNotFoundExeception(String.format("Category %s does not exist in the database", categoryName)));
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

    private Product updateProductFields(ProductRequest productRequest, Product product) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setAvailable(product.isAvailable());
        product.setPrice(productRequest.getPrice());
        product.setTags(productRequest.getTags());
        product.getCategory().setName(productRequest.getCategoryName());
        return product;
    }

}