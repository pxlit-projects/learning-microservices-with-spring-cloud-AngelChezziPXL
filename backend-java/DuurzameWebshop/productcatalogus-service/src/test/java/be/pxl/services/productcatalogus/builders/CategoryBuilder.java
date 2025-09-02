package be.pxl.services.productcatalogus.builders;

import be.pxl.services.productcatalogus.domain.Category;
import java.util.List;
import java.util.Random;

public class CategoryBuilder {
    private Random random = new Random();
    private Category category;

    public CategoryBuilder() {
        final List<Category> CATEGORIES = SampleCategories.getCategories();
        this.category = CATEGORIES.get(this.random.nextInt(CATEGORIES.size()));
    }
    public Category build() {
        return this.category;
    }
}
