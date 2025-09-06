package be.pxl.services.productcatalog.controller;

import be.pxl.services.productcatalog.domain.dto.ProductRequest;
import be.pxl.services.productcatalog.domain.dto.ProductResponse;
import be.pxl.services.productcatalog.exception.AuthorizationException;
import be.pxl.services.productcatalog.service.IProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;
    private final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)                  // nodig voor de ShoppingCart MS
    public List<ProductResponse> getAllProducts() {
        LOG.info("Get all products called.");
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)                  // nodig voor de ShoppingCart MS
    public ProductResponse getProductById(@PathVariable Long id) {
        LOG.info("Get product  bu id called.");
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)             // om producten te kunnen toevoegen
    public void createProduct(@RequestHeader Map<String,String> headers, @Valid @RequestBody ProductRequest productRequest) throws JsonProcessingException {
        LOG.info("Create product called.");
        checkAuthorization(headers);
        long userId = Long.parseLong(headers.get("USER_ID"));
        productService.addProduct(userId, productRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)                      // om te kunnen updaten, categoriseren, labelen van producten
    public void updateProduct(@RequestHeader Map<String,String> headers,@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) throws JsonProcessingException {
        checkAuthorization(headers);
        long userId = Long.parseLong(headers.get("USER_ID"));
        productService.updateProduct(userId, id, productRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProduct(@RequestHeader Map<String,String> headers,@PathVariable Long id) throws JsonProcessingException {
        checkAuthorization(headers);
        long userId = Long.parseLong(headers.get("USER_ID"));
        productService.deleteProduct(userId, id);
    }

    //PRIVATE HELPER METHODS
    private void checkAuthorization(Map<String, String> headers) {
        String role = headers.get("ROLE");
        long userId = Long.parseLong(headers.get("USER_ID"));
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
