package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Product;
import be.pxl.services.productcatalogus.domain.Tag;
import be.pxl.services.productcatalogus.domain.dto.ProductRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductResponse;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static ProductResponse mapProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static Product mapProductRequestToProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }

    public static List<String> convertInputStringToListOfStrings(String tags) {
        return Arrays.stream(tags.split(","))     // Split string by commas into stream
                .map(String::trim)                      // Trim white space for each tag
                .filter(tag -> !tag.isEmpty())      // Filter out empty tags
                .collect(Collectors.toList());           // Collect the result into a list
    }



    //TODO: mappers voor category
    //TODO: mappers voor tag
}
