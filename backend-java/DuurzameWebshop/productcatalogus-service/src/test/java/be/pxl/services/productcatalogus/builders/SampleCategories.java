package be.pxl.services.productcatalogus.builders;

import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleCategories {
    public static List<Category> getCategories() {
        return  new ArrayList<>(Arrays.asList(
                new Category(1L, "tv", new ArrayList<Product>()),
                new Category(2L, "laptop", new ArrayList<Product>()),
                new Category(3L, "pc", new ArrayList<Product>()),
                new Category(4L, "phone", new ArrayList<Product>())
        ));
    }
}
