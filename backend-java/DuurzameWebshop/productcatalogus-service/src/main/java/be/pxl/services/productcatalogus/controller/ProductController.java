package be.pxl.services.productcatalogus.controller;

import be.pxl.services.productcatalogus.domain.Utils.TagHelperMethods;
import be.pxl.services.productcatalogus.domain.dto.CategoryRequest;
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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
    }

    @PostMapping("/{id}/category")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest categoryRequest) {
        productService.setCategory(id, categoryRequest);
    }

    @PostMapping("{id}/tag")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addTagsToProduct(@PathVariable Long id, @RequestBody TagRequest tagRequest) {
        List<String> tagsNames = TagHelperMethods.convertInputStringToListOfStrings(tagRequest.getTags());
        productService.addTagsToProduct(id, tagsNames);
    }
}
