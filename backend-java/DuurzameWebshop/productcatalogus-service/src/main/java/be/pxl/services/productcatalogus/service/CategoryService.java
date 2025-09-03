package be.pxl.services.productcatalogus.service;


import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.controller.dto.CategoryRecord;
import be.pxl.services.productcatalogus.controller.dto.CategoryRequest;
import be.pxl.services.productcatalogus.exception.ConflictException;
import be.pxl.services.productcatalogus.exception.ResourceNotFoundExeception;
import be.pxl.services.productcatalogus.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryRecord> findAll() {
        return this.mapCategoryListToCategoryRecordList(categoryRepository.findAll());
    }

    @Override
    public CategoryRecord findCategoryById(Long id) {
        Category category =categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeception("Category not found"));
        return this.mapCategoryToCategoryRecord(category);
    }

    @Override
    public CategoryRecord findCategoryByName(String name) {
        Category category =categoryRepository.findByName(name.toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundExeception("Category not found"));
        return this.mapCategoryToCategoryRecord(category);
    }

    @Override
    public void addCategory(CategoryRequest categoryRequest) {
        String categoryName = categoryRequest.getCategoryName().trim().toLowerCase();
        Category category = categoryRepository.findByName(categoryName.toLowerCase()).orElse(null);
        if (category != null) {
            throw new ConflictException("Category with name " + categoryName + " already exists");
        }

        category = this.mapCategoryRequestToCategory(categoryRequest);
        categoryRepository.save(category);
    }

    @Override
    public void updateCategoryName(Long id, String categoryName) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeception("Category with id = " + id + " not found"));
        if (category == null) {
            throw new ResourceNotFoundExeception("Category with id " + id + " not found");
        }
        if(categoryRepository.findByName(categoryName.toLowerCase()).orElse(null) != null) {
            throw new ConflictException("Category with name " + categoryName + " already exists");
        }


        category.setName(categoryName.toLowerCase());
        categoryRepository.save(category);
    }

    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse( null   );
        if(category == null) throw new ResourceNotFoundExeception("Category with id = " + id + " not found");
        categoryRepository.deleteById(id);
    }


    // Class helper methods
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
