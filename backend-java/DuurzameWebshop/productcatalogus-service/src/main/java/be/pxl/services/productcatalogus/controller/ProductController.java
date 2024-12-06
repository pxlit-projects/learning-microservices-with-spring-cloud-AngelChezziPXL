package be.pxl.services.productcatalogus.controller;

import be.pxl.services.productcatalogus.domain.Utils.TagHelperMethods;
import be.pxl.services.productcatalogus.domain.dto.ProductRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductResponse;
import be.pxl.services.productcatalogus.domain.dto.TagRequest;
import be.pxl.services.productcatalogus.service.IProductService;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
    }

    @PutMapping("/{productId}/category/{categoryId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCategory(@PathVariable Long productId, @Valid @PathVariable Long categoryId) {
        productService.updateProductCategory(productId, categoryId);
    }


    @PostMapping("{id}/tag")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addTagsToProduct(@PathVariable Long id, @RequestBody TagRequest tagRequest) {
        List<String> tagsNames = TagHelperMethods.convertTagsStringToTagStringList(tagRequest.getTags());
        productService.addTagsToProduct(id, tagsNames);
    }
}
