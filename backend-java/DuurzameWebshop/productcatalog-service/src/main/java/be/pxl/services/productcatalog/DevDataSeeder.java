package be.pxl.services.productcatalog;

import be.pxl.services.productcatalog.domain.Category;
import be.pxl.services.productcatalog.domain.Product;
import be.pxl.services.productcatalog.repository.ICategoryRepository;
import be.pxl.services.productcatalog.repository.IProductRepository;
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
    private final IProductRepository IProductRepository;
    private final ICategoryRepository ICategoryRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // seed only when db is empty
        if(ICategoryRepository.count() > 0 || IProductRepository.count() > 0) return;
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
        ICategoryRepository.saveAll(byName.values());


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
                            n -> ICategoryRepository.findByName(n).orElseThrow()
                    );
                    prod.setCategory(cat);

                    List<String> tags = new ArrayList<>();
                    p.get("tags").forEach(t -> tags.add(t.asText()));
                    prod.setTags(tags);

                    IProductRepository.save(prod);
                }
            }
        }
    }
}
