package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.dto.CategoryRecord;
import be.pxl.services.productcatalogus.domain.dto.CategoryRequest;

import java.util.List;

public interface ICategoryService {
    List<CategoryRecord> findAll();
    CategoryRecord findCategoryById(Long id);
    void addCategory(CategoryRequest categoryRequest);

    void updateCategoryName(Long id, String categoryName);
    void deleteCategoryById(Long id);
}
