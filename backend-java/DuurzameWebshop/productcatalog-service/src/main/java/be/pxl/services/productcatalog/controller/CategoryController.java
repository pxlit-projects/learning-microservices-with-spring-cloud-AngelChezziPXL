package be.pxl.services.productcatalog.controller;

import be.pxl.services.productcatalog.domain.dto.CategoryRecord;
import be.pxl.services.productcatalog.domain.dto.CategoryRequest;
import be.pxl.services.productcatalog.exception.AuthorizationException;
import be.pxl.services.productcatalog.service.ICategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/category", headers = "ROLE")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;
    private Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryRecord> getAllCategories(@RequestHeader Map<String, String> headers) {
        LOG.info("getAllCategories");
        checkAuthorization(headers);
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryRecord getCategoryById(@RequestHeader Map<String, String> headers, @PathVariable @Positive Long id) {
        LOG.info("Fetching categories by id ...");
        checkAuthorization(headers);
        return categoryService.findCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestHeader Map<String, String> headers, @Valid @RequestBody CategoryRequest categoryRequest) {
        LOG.info("Creating new Category...");
        checkAuthorization(headers);
        categoryService.addCategory(categoryRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCategoryName(@RequestHeader Map<String, String> headers, @PathVariable @Positive Long id, @Valid @RequestBody CategoryRequest categoryRequest) {
        LOG.info("Updating CategoryName ...");
        checkAuthorization(headers);
        String categoryName = categoryRequest.getCategoryName().trim().toLowerCase();
        categoryService.updateCategoryName(id, categoryName);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@RequestHeader Map<String, String> headers, @PathVariable @Positive Long id) {
        LOG.info("Deleting category...");
        checkAuthorization(headers);
        categoryService.deleteCategoryById(id);
    }

    private void checkAuthorization(Map<String, String> headers) {
        String role = headers.get("ROLE");
        if (!"admin".equalsIgnoreCase(role)) {
            LOG.debug("You are not authorized to access the logbook");
            throw new AuthorizationException("You are not allowed to access this resource.");
        }
    }
}


//TODO: ONLY ADMIN USER