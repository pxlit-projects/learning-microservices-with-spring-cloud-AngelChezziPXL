package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.dto.CategoryRecord;
import be.pxl.services.productcatalogus.domain.dto.CategoryRequest;

import java.util.List;

public interface ICategoryService {
    public CategoryRecord findCategoryById(Long id);
    public List<CategoryRecord> findAll();
    public CategoryRecord findCategoryByName(String name);
    public void addCategory(CategoryRequest categoryRequest);

    public void updateCategoryName(Long id, String categoryName);
    public void deleteCategoryById(Long id);
}
