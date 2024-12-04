package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.Utils.ProductHelperMethods;
import be.pxl.services.productcatalogus.domain.Product;
import be.pxl.services.productcatalogus.domain.Tag;
import be.pxl.services.productcatalogus.domain.contracts.CategoryContract;
import be.pxl.services.productcatalogus.domain.contracts.ProductContract;
import be.pxl.services.productcatalogus.domain.dto.CategoryRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductResponse;
import be.pxl.services.productcatalogus.repository.CategoryRepository;
import be.pxl.services.productcatalogus.repository.ProductRepository;
import be.pxl.services.productcatalogus.repository.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static be.pxl.services.productcatalogus.domain.Utils.CategoryHelperMethods.mapCategoryRequestToCategory;
import static be.pxl.services.productcatalogus.domain.contracts.ProductContract.validateCategoryOfProductRequest;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();
        return ProductHelperMethods.mapProductListToProductResponseList(products);
    }

    @Override
    public void addProduct(ProductRequest productRequest) {

        // Checks if the category is in the database
        validateCategoryOfProductRequest(productRequest, this.getValidCategoriesStringList());
        Product newProduct = ProductHelperMethods.mapProductRequestToProduct(productRequest);

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

    @Override
    public void setCategory(Long id, CategoryRequest categoryRequest) {
        CategoryContract.validateCategoryRequest(categoryRequest, this.getValidCategoriesStringList());
        Category category = mapCategoryRequestToCategory(categoryRequest);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with 'id = " + id +"' not found."));
        product.setCategory(category);
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void updateProductCategory(Long productId, Long categoryId) {
        // TODO: correct method so that it checks if the category already exists
        // Fetch the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with 'id = " + productId + "' not found."));

        // Fetch the category
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Category with 'id = " + categoryId + "' not found." ));

        // Set the category for the product
        product.setCategory(category);

        // Save the product
        productRepository.save(product);
    }

    @Override
    public void addTagsToProduct(Long productId, List<String> tagTexts) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with 'id = " + productId + "' not found."));

        for (String tagText : tagTexts) {
            Tag tag = tagRepository.findByText(tagText)
                    .orElse(null);
            if (tag == null) {
                tag = Tag.builder()
                        .text(tagText)
                        .build();
                tag.addProduct(product);
                tagRepository.save(tag);
            }
            if (!product.getTags().contains(tag)) {
                product.addTag(tag);
                productRepository.save(product);
            }
        }
    }


    // other methods
    private List<String> getValidCategoriesStringList() {
        return categoryRepository.findAll()
                .stream()
                .map(Category::getName)
                .toList();
    }
}