package be.pxl.services.productcatalogus.domain.contracts;

import be.pxl.services.productcatalogus.domain.Category;

public class CategoryContract {
    static void validateCategoryName(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
    }
}
