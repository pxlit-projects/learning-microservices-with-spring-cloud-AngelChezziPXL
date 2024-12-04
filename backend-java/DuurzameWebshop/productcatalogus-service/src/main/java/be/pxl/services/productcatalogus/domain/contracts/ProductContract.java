package be.pxl.services.productcatalogus.domain.contracts;

import be.pxl.services.productcatalogus.domain.Product;
import be.pxl.services.productcatalogus.domain.dto.ProductRequest;
import be.pxl.services.productcatalogus.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductContract {
    private final ICategoryService categoryService;

    /**
     * Validates the given product against business rules.
     *
     * @param product The product to validate
     * @throws IllegalArgumentException if validation fails
     */
    public static void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        //TODO: if possible customize exception
        if (product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Product description cannot be null or empty.");
        }
//        if (product.getCategory() == null) {
//            throw new IllegalArgumentException("Product category cannot be null");
//        }
        if (product.getPrice() == null || product.getPrice().doubleValue() <= 0) {
            throw new IllegalArgumentException("Product price cannot be null or smaller than equal to zero.");
        }
    }

    /**
     * Validates whether the category of a given product request is valid.
     * This method checks if the category provided in the product request exists in the list of valid categories.
     *
     * @param productRequest the product request object containing the category to be validated
     * @param validCategories a list of valid category names to check against
     *
     * @throws ResponseStatusException if the product request is null or the category is not in the list of valid categories.
     *         - HttpStatus.BAD_REQUEST: if the product request is null or the category is invalid.
     *
     * Example:
     * validCategories = ["Electronics", "Clothing", "Books"]
     * productRequest.getCategory() = "Toys"
     * -> Throws ResponseStatusException: "Category is not valid. Category should be one of [Electronics, Clothing, Books]"
     */
    public static void validateCategoryOfProductRequest(ProductRequest productRequest, List<String> validCategories) {
        String requestCategory = productRequest.getCategory();
        if (requestCategory == null) {
            return;
        }
        if (!validCategories.contains(productRequest.getCategory())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category is not valid. Category should be one of " + validCategories);
        }
    }
}
