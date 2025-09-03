package be.pxl.services.productcatalogus;

import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.domain.Product;
import be.pxl.services.productcatalogus.repository.CategoryRepository;
import be.pxl.services.productcatalogus.repository.ProductRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DevDataSeeder implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // seed only when db is empty
        if(categoryRepository.count() > 0 || productRepository.count() > 0) return;
        JsonNode root = objectMapper.readTree(new ClassPathResource("seed-data.json").getInputStream());

        // 1) Create/save categories first
        Map<String, Category> byName = new HashMap<>();
        for (JsonNode block : root) {
            if (block.has("categories")) {
                for (JsonNode c : block.get("categories")) {
                    String name = c.get("name").asText();
                    Category cat = new Category();
                    cat.setName(name);
                    byName.put(name, cat);
                }
            }
        }
        categoryRepository.saveAll(byName.values());


        // 2) Create products and link to category by name
        for (JsonNode block : root) {
            if (block.has("products")) {
                for (JsonNode p : block.get("products")) {
                    Product prod = new Product();
                    prod.setName(p.get("name").asText());
                    prod.setDescription(p.get("description").asText());
                    prod.setAvailable(p.get("available").asBoolean());
                    prod.setPrice(Double.parseDouble(p.get("price").toString()));

                    String categoryName = p.get("categoryName").asText();
                    Category cat = byName.computeIfAbsent(
                            categoryName,
                            n -> categoryRepository.findByName(n).orElseThrow()
                    );
                    prod.setCategory(cat);

                    List<String> tags = new ArrayList<>();
                    p.get("tags").forEach(t -> tags.add(t.asText()));
                    prod.setTags(tags);

                    productRepository.save(prod);
                }
            }
        }
    }
}
