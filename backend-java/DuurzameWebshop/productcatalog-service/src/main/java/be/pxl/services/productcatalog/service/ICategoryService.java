package be.pxl.services.productcatalog.service;

import be.pxl.services.productcatalog.domain.dto.CategoryRecord;
import be.pxl.services.productcatalog.domain.dto.CategoryRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ICategoryService {
    List<CategoryRecord> findAll();
    CategoryRecord findCategoryById(Long id);
    CategoryRecord findCategoryByName(String name);
    void addCategory(long userId, CategoryRequest categoryRequest);
    void updateCategoryName(long userId, Long id, String categoryName) throws JsonProcessingException;
    void deleteCategoryById(long userId, Long id) throws JsonProcessingException;
}
