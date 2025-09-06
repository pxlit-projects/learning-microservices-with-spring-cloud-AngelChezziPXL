package be.pxl.services.productcatalog.controller;

import be.pxl.services.productcatalog.domain.dto.CategoryRecord;
import be.pxl.services.productcatalog.domain.dto.CategoryRequest;
import be.pxl.services.productcatalog.exception.AuthorizationException;
import be.pxl.services.productcatalog.service.ICategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
@RequestMapping(value="/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;
    private Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryRecord> getAllCategories(@RequestHeader Map<String, String> headers) {
        LOG.info("getAllCategories");
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryRecord getCategoryById(@RequestHeader Map<String, String> headers, @PathVariable @Positive Long id) {
        LOG.info("Fetching categories by id ...");
        return categoryService.findCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestHeader Map<String, String> headers, @Valid @RequestBody CategoryRequest categoryRequest) {
        LOG.info("Creating new Category...");
        checkAuthorization(headers);
        long userId = Long.parseLong(headers.get("user_id"));
        categoryService.addCategory(userId, categoryRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCategoryName(@RequestHeader Map<String, String> headers, @PathVariable @Positive Long id, @Valid @RequestBody CategoryRequest categoryRequest) throws JsonProcessingException {
        LOG.info("Updating CategoryName ...");
        checkAuthorization(headers);
        String categoryName = categoryRequest.getCategoryName().trim().toLowerCase();
        long userId = Long.parseLong(headers.get("user_id"));
        categoryService.updateCategoryName(userId, id, categoryName);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@RequestHeader Map<String, String> headers, @PathVariable @Positive Long id) throws JsonProcessingException {
        LOG.info("Deleting category...");
        checkAuthorization(headers);
        long userId = Long.parseLong(headers.get("user_id"));
        categoryService.deleteCategoryById(userId, id);
    }

    //PRIVATE HELPER METHODS
    private void checkAuthorization(Map<String, String> headers) {
        String role = headers.get("role");
        long userId = headers.get("user_id") != null ? Long.parseLong(headers.get("user_id")): 0;
        if (!role.equalsIgnoreCase("admin")) {
            LOG.debug("You are not authorized to access the logbook");
            throw new AuthorizationException("You are not allowed to access this resource.");
        }

        if(userId < 1) {
            LOG.debug("User id cannot be null");
            throw new AuthorizationException("User id cannot be null or zero");
        }
    }

}


//TODO: ONLY ADMIN USER