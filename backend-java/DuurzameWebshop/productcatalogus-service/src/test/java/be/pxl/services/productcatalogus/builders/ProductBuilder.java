package be.pxl.services.productcatalogus.builders;

import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.Product;
import java.util.*;

public class ProductBuilder {
    private Random random = new Random();
    private Product product;
    private final List<Category> CATEGORIES;
    private HashMap<String , List<String>> tagHashMap = new HashMap<>();

    public ProductBuilder() {
        loadTagsFromSampleTags();
        this.CATEGORIES = SampleCategories.getCategories();

        Category category = CATEGORIES.get(random.nextInt(CATEGORIES.size()));
        List<String> tags = getRandomTags(category.getName());

        this.product = new Product();
        this.product.setId(random.nextLong(1L,10000L));
        this.product.setName(UUID.randomUUID().toString());
        this.product.setDescription(UUID.randomUUID().toString());
        this.product.setPrice(random.nextDouble(1.0,3000.0));
        this.product.setAvailable(random.nextBoolean());
        this.product.setCategory(category);
        this.product.setTags(tags);
    }

    public ProductBuilder withId(long id) {
        this.product.setId(id);
        return this;
    }

    public ProductBuilder withName(String name) {
        this.product.setName(name);
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.product.setDescription(description);
        return this;
    }

    public ProductBuilder withPrice(double price) {
        this.product.setPrice(price);
        return this;
    }

    public ProductBuilder withCategory(Category category) {
        this.product.setCategory(category);
        return this;
    }
    public ProductBuilder withTags(List<String> tags) {
        this.product.setTags(tags);
        return this;
    }

    public ProductBuilder withAvailabiltity(boolean availability) {
        this.product.setAvailable(availability);
        return this;
    }

    public Product build() {
        return this.product;
    }


    //PRIVATE METHODS
    private void loadTagsFromSampleTags(){
        tagHashMap.put("tv", SampleTags.getTvTags());
        tagHashMap.put("laptop", SampleTags.getLaptopTags());
        tagHashMap.put("pc", SampleTags.getPcTags());
        tagHashMap.put("phone", SampleTags.getPhoneTags());
    }

    private Category getRandomCategory() {
        return CATEGORIES.get(random.nextInt(CATEGORIES.size()));
    }

    private List<String> getRandomTags(String categoryName) {
        int numberOfTagElements = random.nextInt(1,5);

        List<String> tags = tagHashMap.get(categoryName);
        List<String> randomSelectedTags = new ArrayList<>();
        List<Integer> alreadyUsedIndexes = new ArrayList<>();
        for (int i = 0; i < numberOfTagElements; i++) {
            int randomTagIndex = random.nextInt(tags.size());
            if (!alreadyUsedIndexes.contains(randomTagIndex)) {
                alreadyUsedIndexes.add(randomTagIndex);
                randomSelectedTags.add(tags.get(randomTagIndex));
            }
        }
        return randomSelectedTags;


    }
}
