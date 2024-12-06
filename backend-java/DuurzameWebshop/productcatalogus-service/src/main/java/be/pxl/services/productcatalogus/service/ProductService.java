package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.Utils.ProductHelperMethods;
import be.pxl.services.productcatalogus.domain.Product;
import be.pxl.services.productcatalogus.domain.Tag;
import be.pxl.services.productcatalogus.domain.Utils.TagHelperMethods;
import be.pxl.services.productcatalogus.domain.contracts.CategoryContract;
import be.pxl.services.productcatalogus.domain.dto.CategoryRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductResponse;
import be.pxl.services.productcatalogus.repository.CategoryRepository;
import be.pxl.services.productcatalogus.repository.ProductRepository;
import be.pxl.services.productcatalogus.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final TagService tagService;


    @Override
    public List<ProductResponse> findAll() {
        return ProductHelperMethods.mapProductListToProductResponseList(productRepository.findAll());
    }

    @Override
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product with id %s not found", id)));
        return ProductHelperMethods.mapProductToProductResponse(product);
    }

    @Override
    public void addProduct(ProductRequest productRequest) {
        // map productRequest to product without category and tags
        Product newProduct = ProductHelperMethods.mapProductRequestToProduct(productRequest);
        // Save product to DB to get an Id
        Product newDbProduct = productRepository.save(newProduct);
        this.updateTagAndCategoryValues(newDbProduct, productRequest);
    }

    @Override
    public void updateProduct(Long id, ProductRequest productRequest) {
        Product oldProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product with 'id = " + id + "' not found."));

        Product updatedProduct = ProductHelperMethods.mapProductRequestToProduct(productRequest);
        updatedProduct.setId(id);

        this.updateTagAndCategoryValues(updatedProduct, productRequest);
        // Save changes not needed because it is already saved in another method
        //productRepository.save(newProduct);
    }

    @Override
    public void setCategory(Long id, CategoryRequest categoryRequest) {
        CategoryContract.validateCategoryRequest(categoryRequest, this.getValidCategoriesStringList());
        Category category = categoryRepository.findByName(categoryRequest.getCategoryName().trim())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with name %s not found", categoryRequest.getCategoryName())));
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with 'id = " + id + "' not found."));
        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    public void updateProductCategory(Long productId, Long categoryId) {
        // TODO: correct method so that it checks if the category already exists
        // Fetch the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with 'id = " + productId + "' not found."));

        // Fetch the category
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category with 'id = " + categoryId + "' not found."));

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

    private void updateTagAndCategoryValues(Product product, ProductRequest productRequest)
    {
        //Set category
        String categoryName = productRequest.getCategory();
        if(categoryName !=null) {
            CategoryRequest categoryRequest = new CategoryRequest(categoryName);
            this.setCategory(product.getId(), categoryRequest);
        }

        //Set tags
        String tagsString = productRequest.getTags();
        List<String> tagStringList = TagHelperMethods.convertTagsStringToTagStringList(tagsString);
        Set<Tag> tags = new HashSet<>();
        for (String tagString : tagStringList) {
            tags.add(tagService.addTag(tagString));
        }
        product.setTags(tags);
    }
}