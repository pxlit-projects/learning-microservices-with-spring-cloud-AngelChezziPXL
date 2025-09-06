package be.pxl.services.productcatalog.service;


import be.pxl.services.productcatalog.domain.Category;
import be.pxl.services.productcatalog.domain.Product;
import be.pxl.services.productcatalog.domain.dto.CategoryRecord;
import be.pxl.services.productcatalog.domain.dto.CategoryRequest;
import be.pxl.services.productcatalog.domain.dto.ProductQueueMessage;
import be.pxl.services.productcatalog.domain.dto.ProductResponse;
import be.pxl.services.productcatalog.exception.ConflictException;
import be.pxl.services.productcatalog.exception.ResourceNotFoundException;
import be.pxl.services.productcatalog.repository.ICategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepository ICategoryRepository;
    private final IRabbitMqService rabbitMqService;
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Override
    public List<CategoryRecord> findAll() {
        log.info("Find all categories");
        return this.mapCategoryListToCategoryRecordList(ICategoryRepository.findAll());
    }

    @Override
    public CategoryRecord findCategoryById(Long id) {
        log.info("Find category by id: {}", id);
        Category category = ICategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return this.mapCategoryToCategoryRecord(category);
    }

    @Override
    public CategoryRecord findCategoryByName(String name) {
        log.info("Find category by name: {}", name);
        Category category = ICategoryRepository.findByName(name.toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return this.mapCategoryToCategoryRecord(category);
    }

    @Override
    public void addCategory(long userId, CategoryRequest categoryRequest) {
        log.info("Add category: {}", categoryRequest);
        String categoryName = categoryRequest.getCategoryName().trim().toLowerCase();
        Category category = ICategoryRepository.findByName(categoryName.toLowerCase()).orElse(null);
        if (category != null) {
            log.info("Category already exists: {}", category);
            throw new ConflictException("Category with name " + categoryName + " already exists");
        }

        category = this.mapCategoryRequestToCategory(categoryRequest);
        ICategoryRepository.save(category);
    }

    @Override
    public void updateCategoryName(long userId, Long id, String categoryName) throws JsonProcessingException {
        log.info("Update category name to '{}' for category with id {}", categoryName, id);
        Category category = ICategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with id = " + id + " not found"));
        if (category == null) {
            log.error("Category with id {} not found", id);
            throw new ResourceNotFoundException("Category with id " + id + " not found");
        }
        if(ICategoryRepository.findByName(categoryName.toLowerCase()).orElse(null) != null) {
            log.error("Category with name {} already exists", categoryName);
            throw new ConflictException("Category with name " + categoryName + " already exists");
        }
        category.setName(categoryName.toLowerCase());
        publishChangesToQueue(userId, category, false);
        ICategoryRepository.save(category);


    }

    public void deleteCategoryById(long userId, Long id) throws JsonProcessingException {
        log.info("Delete category by id: {}", id);
        Category category = ICategoryRepository.findById(id).orElse( null   );
        if(category == null) throw new ResourceNotFoundException("Category with id = " + id + " not found");
        publishChangesToQueue(userId, category, true);
        ICategoryRepository.deleteById(id);

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


    // PRIVATE METHODS
    private void publishChangesToQueue(long userId, Category category, boolean clearAllResponses) throws JsonProcessingException {
        List<ProductResponse> productResponseList = category.getProducts().stream().map(Product::toProductResponse).toList();

        for (ProductResponse productResponse : productResponseList) {
            ProductQueueMessage productQueueMessage = ProductQueueMessage.builder()
                    .serviceName("productcatalog_service")
                    .userId(userId)
                    .productResponse(productResponse)
                    .build();

            if (clearAllResponses) {
                ProductResponse emptyProductResponse = new ProductResponse();
                productQueueMessage.setProductResponse(emptyProductResponse);
            }

            rabbitMqService.sendMessageToQueue(productQueueMessage);
        }
    }



}
