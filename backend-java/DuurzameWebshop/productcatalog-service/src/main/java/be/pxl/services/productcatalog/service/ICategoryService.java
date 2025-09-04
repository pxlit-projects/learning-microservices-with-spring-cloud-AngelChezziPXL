package be.pxl.services.productcatalog.service;

import be.pxl.services.productcatalog.controller.dto.CategoryRecord;
import be.pxl.services.productcatalog.controller.dto.CategoryRequest;

import java.util.List;

public interface ICategoryService {
    List<CategoryRecord> findAll();
    CategoryRecord findCategoryById(Long id);
    CategoryRecord findCategoryByName(String name);
    void addCategory(CategoryRequest categoryRequest);
    void updateCategoryName(Long id, String categoryName);
    void deleteCategoryById(Long id);
}
