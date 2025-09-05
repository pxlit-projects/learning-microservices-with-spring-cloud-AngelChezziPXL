package be.pxl.services.productcatalog.service;


import be.pxl.services.productcatalog.domain.Category;
import be.pxl.services.productcatalog.domain.dto.CategoryRecord;
import be.pxl.services.productcatalog.domain.dto.CategoryRequest;
import be.pxl.services.productcatalog.exception.ConflictException;
import be.pxl.services.productcatalog.exception.ResourceNotFoundException;
import be.pxl.services.productcatalog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Override
    public List<CategoryRecord> findAll() {
        log.info("Find all categories");
        return this.mapCategoryListToCategoryRecordList(categoryRepository.findAll());
    }

    @Override
    public CategoryRecord findCategoryById(Long id) {
        log.info("Find category by id: {}", id);
        Category category =categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return this.mapCategoryToCategoryRecord(category);
    }

    @Override
    public CategoryRecord findCategoryByName(String name) {
        log.info("Find category by name: {}", name);
        Category category =categoryRepository.findByName(name.toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return this.mapCategoryToCategoryRecord(category);
    }

    @Override
    public void addCategory(CategoryRequest categoryRequest) {
        log.info("Add category: {}", categoryRequest);
        String categoryName = categoryRequest.getCategoryName().trim().toLowerCase();
        Category category = categoryRepository.findByName(categoryName.toLowerCase()).orElse(null);
        if (category != null) {
            log.info("Category already exists: {}", category);
            throw new ConflictException("Category with name " + categoryName + " already exists");
        }

        category = this.mapCategoryRequestToCategory(categoryRequest);
        categoryRepository.save(category);
    }

    @Override
    public void updateCategoryName(Long id, String categoryName) {
        log.info("Update category name to '{}' for category with id {}", categoryName, id);
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with id = " + id + " not found"));
        if (category == null) {
            log.error("Category with id {} not found", id);
            throw new ResourceNotFoundException("Category with id " + id + " not found");
        }
        if(categoryRepository.findByName(categoryName.toLowerCase()).orElse(null) != null) {
            log.error("Category with name {} already exists", categoryName);
            throw new ConflictException("Category with name " + categoryName + " already exists");
        }
        category.setName(categoryName.toLowerCase());
        categoryRepository.save(category);
    }

    public void deleteCategoryById(Long id) {
        log.info("Delete category by id: {}", id);
        Category category = categoryRepository.findById(id).orElse( null   );
        if(category == null) throw new ResourceNotFoundException("Category with id = " + id + " not found");
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
