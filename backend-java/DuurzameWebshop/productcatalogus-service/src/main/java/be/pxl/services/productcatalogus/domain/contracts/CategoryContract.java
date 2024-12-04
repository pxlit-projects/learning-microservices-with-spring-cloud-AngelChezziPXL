package be.pxl.services.productcatalogus.domain.contracts;

import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.dto.CategoryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class CategoryContract {
    public static void validateCategoryName(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
    }

    public static void validateCategoryRequest(CategoryRequest categoryRequest, List<String> validCategories) {
        if (categoryRequest == null) {
            return;
        }

        if (!validCategories.contains(categoryRequest.getCategoryName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category is not valid. Category should be one of " + validCategories);
        }
    }
}
