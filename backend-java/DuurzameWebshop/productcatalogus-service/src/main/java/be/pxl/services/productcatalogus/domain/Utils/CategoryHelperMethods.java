package be.pxl.services.productcatalogus.domain.Utils;

import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.dto.CategoryRecord;
import be.pxl.services.productcatalogus.domain.dto.CategoryRequest;

import java.util.List;

public class CategoryHelperMethods {
    public static Category mapCategoryRequestToCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.getCategoryName()
                        .trim()
                        .toLowerCase())
                .build();
    }

    public static CategoryRecord mapCategoryToCategoryRecord(Category category) {
        return new CategoryRecord(category.getId(), category.getName());
    }

    public static List<CategoryRecord> mapCategoryListToCategoryRecordList(List<Category> categories) {
        return categories.stream()
                .map(CategoryHelperMethods::mapCategoryToCategoryRecord)
                .toList();
    }

}
