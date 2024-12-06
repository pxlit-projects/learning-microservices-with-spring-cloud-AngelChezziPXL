package be.pxl.services.productcatalogus.service;


import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.dto.CategoryRecord;
import be.pxl.services.productcatalogus.domain.dto.CategoryRequest;
import be.pxl.services.productcatalogus.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryRecord> findAll() {
        return this.mapCategoryListToCategoryRecordList(categoryRepository.findAll());

    }

    @Override
    public CategoryRecord findCategoryById(Long id) {
        Category category =categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return this.mapCategoryToCategoryRecord(category);
    }

    @Override
    public CategoryRecord findCategoryByName(String name) {
        Category category =categoryRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return this.mapCategoryToCategoryRecord(category);
    }

    @Override
    public void addCategory(CategoryRequest categoryRequest) {
        String categoryName = categoryRequest.getCategoryName().trim().toLowerCase();
        if(categoryNameExists(categoryName)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category '" + categoryName + "' name already exists");
        }
        Category category = this.mapCategoryRequestToCategory(categoryRequest);
        categoryRepository.save(category);
    }

    @Override
    public void updateCategoryName(Long id, String categoryName) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        category.setName(categoryName);
        categoryRepository.save(category);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }


    // Class helper methods
    private boolean categoryNameExists(String name) {
        try {
            categoryRepository.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
            return true;
        } catch (ResponseStatusException e) {
            return false;
        }
    }

    public Category mapCategoryRequestToCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.getCategoryName().trim().toLowerCase())
                .build();
    }

    public CategoryRecord mapCategoryToCategoryRecord(Category category) {
        return new CategoryRecord(category.getId(), category.getName());
    }

    public List<CategoryRecord> mapCategoryListToCategoryRecordList(List<Category> categories) {
        return categories.stream()
                .map(this::mapCategoryToCategoryRecord)
                .toList();
    }

}
