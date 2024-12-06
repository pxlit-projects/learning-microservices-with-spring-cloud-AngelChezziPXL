package be.pxl.services.productcatalogus.service;


import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.Utils.CategoryHelperMethods;
import be.pxl.services.productcatalogus.domain.contracts.CategoryContract;
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
    public CategoryRecord findCategoryById(Long id) {
        Category category =categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return CategoryHelperMethods.mapCategoryToCategoryRecord(category);
    }

    @Override
    public List<CategoryRecord> findAll() {
        return CategoryHelperMethods.mapCategoryListToCategoryRecordList(categoryRepository.findAll());

    }

    @Override
    public CategoryRecord findCategoryByName(String name) {
        Category category =categoryRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return CategoryHelperMethods.mapCategoryToCategoryRecord(category);
    }

    @Override
    public void addCategory(CategoryRequest categoryRequest) {
        if(categoryNameExists(categoryRequest.getCategoryName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category name already exists");
        }
        Category category = CategoryHelperMethods.mapCategoryRequestToCategory(categoryRequest);
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

}
