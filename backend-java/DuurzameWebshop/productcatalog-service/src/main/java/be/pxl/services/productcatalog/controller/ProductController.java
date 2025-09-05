package be.pxl.services.productcatalog.controller;

import be.pxl.services.productcatalog.domain.dto.ProductRequest;
import be.pxl.services.productcatalog.domain.dto.ProductResponse;
import be.pxl.services.productcatalog.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;
    private Long userId = 1L;           //TODO: adapt to take it from the header

    @GetMapping
    @ResponseStatus(HttpStatus.OK)                  // nodig voor de ShoppingCart MS
    public List<ProductResponse> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)                  // nodig voor de ShoppingCart MS
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)             // voor producten te kunnen toevoegen
    public void createProduct(@Valid @RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)                      // voor updaten, categoriseren, labelen van producten
    public void updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
