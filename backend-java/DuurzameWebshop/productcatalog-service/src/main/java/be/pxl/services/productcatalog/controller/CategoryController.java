package be.pxl.services.productcatalog.controller;

import be.pxl.services.productcatalog.domain.dto.CategoryRecord;
import be.pxl.services.productcatalog.domain.dto.CategoryRequest;
import be.pxl.services.productcatalog.service.ICategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryRecord> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryRecord getCategoryById(@PathVariable @Positive Long id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryService.addCategory(categoryRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCategoryName(@PathVariable @Positive Long id, @Valid @RequestBody CategoryRequest categoryRequest) {
        String categoryName = categoryRequest.getCategoryName().trim().toLowerCase();
        categoryService.updateCategoryName(id, categoryName);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable @Positive Long id) {
        categoryService.deleteCategoryById(id);
    }
}


//TODO: ONLY ADMIN USER