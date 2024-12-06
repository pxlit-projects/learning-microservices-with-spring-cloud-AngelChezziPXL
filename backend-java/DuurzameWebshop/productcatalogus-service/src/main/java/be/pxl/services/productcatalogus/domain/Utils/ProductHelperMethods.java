package be.pxl.services.productcatalogus.domain.Utils;

import be.pxl.services.productcatalogus.domain.Product;
import be.pxl.services.productcatalogus.domain.dto.ProductRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ProductHelperMethods {
    public static ProductResponse mapProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .categoryRecord(CategoryHelperMethods.mapCategoryToCategoryRecord(product.getCategory()))
                .available(product.isAvailable())
                .tags(product.getTags().stream()
                        .map(TagHelperMethods::mapTagToTagRecord)
                        .toList())
                .price(product.getPrice())
                .build();
    }

    public static Product mapProductRequestToProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .available(productRequest.getAvailable())
                .price(productRequest.getPrice())
                .build();
    }

    public static List<ProductResponse> mapProductListToProductResponseList(List<Product> productList) {
        return productList.stream()
                .map(ProductHelperMethods::mapProductToProductResponse)
                .collect(Collectors.toList());
    }
}
