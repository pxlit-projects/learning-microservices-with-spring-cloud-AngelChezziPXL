package be.pxl.services.winkelwagen.controller;

import be.pxl.services.winkelwagen.controller.dto.ProductDto;
import be.pxl.services.winkelwagen.domain.Product;
import be.pxl.services.winkelwagen.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jdk.jfr.StackTrace;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private final ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public ProductDto updateProduct(@RequestBody @Valid ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
