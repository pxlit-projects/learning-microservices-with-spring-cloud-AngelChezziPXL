package be.pxl.services.productcatalogus.controller;

import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.Utils.CategoryHelperMethods;
import be.pxl.services.productcatalogus.domain.dto.CategoryRecord;
import be.pxl.services.productcatalogus.domain.dto.CategoryRequest;
import be.pxl.services.productcatalogus.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryRecord> getAllCategories() {
        List<Category> dbCategories = categoryService.findAll();
        return CategoryHelperMethods.mapCategoryListToCategoryRecordList(dbCategories);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.addCategory(CategoryHelperMethods.mapCategoryRequestToCategory(categoryRequest));
    }
}
