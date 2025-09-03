package be.pxl.services.productcatalogus.builders;

import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CategoryBuilder {
    private Category category;
    private Random random = new Random();

    public CategoryBuilder() {
        this.category = new Category();
        this.category.setId(random.nextLong(1L,1000L));
        this.category.setName(UUID.randomUUID().toString());
        this.category.setProducts(new ArrayList<>());
    }

    public CategoryBuilder withId(Long id) {
        this.category.setId(id);
        return this;
    }

    public CategoryBuilder withName(String name) {
        this.category.setName(name);
        return this;
    }


    public CategoryBuilder withProducts(List<Product> products) {
        this.category.setProducts(products);
        return this;
    }

    public Category build() {
        return this.category;
    }
}
