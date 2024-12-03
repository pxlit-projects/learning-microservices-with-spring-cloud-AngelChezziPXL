package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.Product;
import be.pxl.services.productcatalogus.domain.Tag;
import be.pxl.services.productcatalogus.domain.contracts.ProductContract;
import be.pxl.services.productcatalogus.domain.dto.CategoryRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductRequest;
import be.pxl.services.productcatalogus.repository.CategoryRepository;
import be.pxl.services.productcatalogus.repository.ProductRepository;
import be.pxl.services.productcatalogus.repository.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void addProduct(ProductRequest productRequest) {
        Product newProduct = Utils.mapProductRequestToProduct(productRequest);

        // Validate new product
        ProductContract.validateProduct(newProduct);

        // Save product to DB
        productRepository.save(newProduct);
    }

    @Override
    public void updateProduct(Long id, ProductRequest productRequest) {
        Product oldProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product with 'id = " + id +"' not found."));

        // Update fields
        oldProduct.setName(productRequest.getName());
        oldProduct.setDescription(productRequest.getDescription());
        oldProduct.setPrice(productRequest.getPrice());

        // Validate the updated product
        ProductContract.validateProduct(oldProduct);

        // Save changes
        productRepository.save(oldProduct);
    }

    @Transactional
    @Override
    public void updateProductCategory(Long productId, Long categoryId) {
        // Fetch the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with 'id = " + productId + "' not found."));

        // Fetch the category
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Set the category for the product
        product.setCategory(category);

        // Save the product
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void addTagsToProduct(Long productId, List<String> tagNames) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with 'id = " + productId + "' not found."));

        for (String tagName : tagNames) {
            Tag tag = tagRepository.findByName(tagName)
                    .orElseGet(() -> tagRepository.save(new Tag(null, tagName, null)));
            product.addTag(tag);
        }
        productRepository.save(product);
    }


}
