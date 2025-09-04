package be.pxl.services.winkelwagen.controller.dto;

import be.pxl.services.winkelwagen.domain.Product;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Long id;
    private Long sellerProductId;
    private String name;
    private String description;
    private Double price;

    public static ProductDto fromProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setSellerProductId(product.getSellerProductId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
