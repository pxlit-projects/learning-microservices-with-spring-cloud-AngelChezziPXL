package be.pxl.services.productcatalog.service;

import be.pxl.services.productcatalog.domain.Category;
import be.pxl.services.productcatalog.domain.Product;
import be.pxl.services.productcatalog.domain.dto.ProductQueueMessage;
import be.pxl.services.productcatalog.domain.dto.ProductRequest;
import be.pxl.services.productcatalog.domain.dto.ProductResponse;
import be.pxl.services.productcatalog.exception.ResourceNotFoundException;
import be.pxl.services.productcatalog.repository.ICategoryRepository;
import be.pxl.services.productcatalog.repository.IProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final IProductRepository IProductRepository;
    private final ICategoryRepository ICategoryRepository;
    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
    private final IRabbitMqService rabbitMqService;

    @Override
    public List<ProductResponse> findAll() {
        LOG.info("Find all products");
        List<Product> products = IProductRepository.findAll();
        return products.stream().map(Product::toProductResponse).toList();
    }

    @Override
    public ProductResponse findById(Long id) {
        LOG.info("Find product by id: {}", id);
        Product product = IProductRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %s not found", id)));
        return product.toProductResponse();
    }

    @Override
    public ProductResponse addProduct(long userId, ProductRequest productRequest) throws JsonProcessingException {
        LOG.info("Adding product: {}", productRequest);
        Product product = IProductRepository.save(mapProductRequestToProduct(productRequest));
        ProductResponse productResponse = product.toProductResponse();
        publishOnRabbitMqQueue(userId, productResponse);
        return productResponse;
    }

    public ProductResponse updateProduct(long userId, long id, ProductRequest productRequest) throws JsonProcessingException {
        LOG.info("Update product: {}", productRequest);
        Product product = IProductRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Product with id %s not found", id)));
        Product updatedProduct = mapProductRequestToProduct(productRequest);
        updatedProduct.setId(product.getId());
        ProductResponse productResponse = IProductRepository.save(updatedProduct).toProductResponse();
        publishOnRabbitMqQueue(userId, productResponse);
        return productResponse;
    }

    @Override
    public void deleteProduct(long userId, long id) throws JsonProcessingException {
        LOG.info("Delete product with id: {}", id);
        if(!IProductRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Product with id %s not found", id));
        }
        IProductRepository.deleteById(id);
        ProductResponse emptyProductResponse = new ProductResponse();
        emptyProductResponse.setId(id);
        publishOnRabbitMqQueue(userId, emptyProductResponse);
    }

    // helper methods (CUSTOM MAPPER)
    private Product mapProductRequestToProduct(ProductRequest productRequest) {
        String categoryName = productRequest.getCategoryName().trim().toLowerCase();
        Category category = ICategoryRepository.findByName(categoryName).orElseThrow(() -> new ResourceNotFoundException(String.format("Category %s does not exist in the database", categoryName)));
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .category(category)
                .tags(productRequest.getTags())
                .available(productRequest.getAvailable())
                .price(productRequest.getPrice())
                .build();
    }

    protected void publishOnRabbitMqQueue(Long userId, ProductResponse productResponse) throws JsonProcessingException {
        LOG.info("Publishing on rabbit mq queue: {}", productResponse);
        ProductQueueMessage productQueueMessage = new ProductQueueMessage();
        productQueueMessage.setUserId(userId);
        productQueueMessage.setServiceName("productcatalog_service");
        productQueueMessage.setProductResponse(productResponse);
        rabbitMqService.sendMessageToQueue(productQueueMessage);
    }




}