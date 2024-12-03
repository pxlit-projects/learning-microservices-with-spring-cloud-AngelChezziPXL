package be.pxl.services.productcatalogus.domain.contracts;

import be.pxl.services.productcatalogus.domain.Product;

import java.math.BigDecimal;

public class ProductContract {
    /**
     * Validates the given product against business rules.
     *
     * @param product The product to validate
     * @throws IllegalArgumentException if validation fails
     */
    public static void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        //TODO: if possible customize exception
        if (product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Product description cannot be null or empty.");
        }
//        if (product.getCategory() == null) {
//            throw new IllegalArgumentException("Product category cannot be null");
//        }
        if (product.getPrice() == null || product.getPrice().doubleValue() <= 0) {
            throw new IllegalArgumentException("Product price cannot be null or smaller than equal to zero.");
        }
    }
}
