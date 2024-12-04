package be.pxl.services.productcatalogus.service;


import be.pxl.services.productcatalogus.domain.Category;
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
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    public boolean categoryExists(String name) {
        try {
            categoryRepository.findByName(name);
            return true;
        } catch (ResponseStatusException e) {
            return false;
        }
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
}
