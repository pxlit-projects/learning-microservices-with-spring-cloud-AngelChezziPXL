package be.pxl.services.productcatalog.config;

import be.pxl.services.productcatalog.domain.Category;
import be.pxl.services.productcatalog.domain.Product;
import be.pxl.services.productcatalog.repository.ICategoryRepository;
import be.pxl.services.productcatalog.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;

    @Override
    public void run(String... args) {
        // ✅ Only seed when DB is empty
        if (categoryRepository.count() > 0 || productRepository.count() > 0) {
            return;
        }

        // Categories
        Category pc = categoryRepository.save(new Category(null, "pc", new ArrayList<>()));
        Category laptop = categoryRepository.save(new Category(null, "laptop", new ArrayList<>()));
        Category phone = categoryRepository.save(new Category(null, "phone", new ArrayList<>()));
        Category tablet = categoryRepository.save(new Category(null, "tablet", new ArrayList<>()));

        // Products (20 examples)
        List<Product> products = List.of(
                new Product(null, "Gaming Tower RTX 4070", "High-performance gaming desktop", 1899.00, true, pc, List.of("gaming","rtx4070")),
                new Product(null, "Office Mini PC", "Compact desktop for productivity", 699.00, true, pc, List.of("office","mini")),
                new Product(null, "All-in-One 24\"", "Space-saving all-in-one desktop", 999.00, true, pc, List.of("aio","24inch")),
                new Product(null, "Workstation Ryzen 9", "Creator workstation for 3D rendering", 2599.00, true, pc, List.of("workstation","ryzen9")),
                new Product(null, "Budget Desktop i3", "Entry-level desktop", 429.00, true, pc, List.of("budget","intel-i3")),

                new Product(null, "Ultrabook 13\"", "Lightweight 13-inch ultrabook", 1199.00, true, laptop, List.of("ultrabook","13inch")),
                new Product(null, "Gaming Laptop 15\"", "15-inch gaming laptop", 1599.00, true, laptop, List.of("gaming","rtx4060")),
                new Product(null, "Business Laptop 14\"", "Durable business laptop", 1399.00, true, laptop, List.of("business","14inch")),
                new Product(null, "2-in-1 Convertible 13\"", "Convertible touchscreen laptop", 1099.00, true, laptop, List.of("convertible","touch")),
                new Product(null, "Student Laptop 15\"", "Affordable laptop for students", 649.00, true, laptop, List.of("student","15inch")),

                new Product(null, "Smartphone Pro 6.1", "Flagship smartphone", 1099.00, true, phone, List.of("flagship","oled")),
                new Product(null, "Budget Phone 6.5", "Affordable phone", 249.00, true, phone, List.of("budget","50mp")),
                new Product(null, "Foldable Phone", "Foldable smartphone", 1699.00, true, phone, List.of("foldable","7.6inch")),
                new Product(null, "Rugged Phone", "Rugged smartphone", 599.00, true, phone, List.of("rugged","ip68")),
                new Product(null, "Camera Phone Plus", "Premium camera phone", 1299.00, true, phone, List.of("camera","1inch-sensor")),

                new Product(null, "Tablet 11", "Portable 11-inch tablet", 599.00, true, tablet, List.of("android","11inch")),
                new Product(null, "Tablet Pro 12.9", "Professional tablet", 1299.00, true, tablet, List.of("pro","12.9inch")),
                new Product(null, "Android Tablet 10", "Balanced 10-inch tablet", 329.00, true, tablet, List.of("android","10inch")),
                new Product(null, "Kids Tablet 8", "Durable kids tablet", 149.00, true, tablet, List.of("kids","8inch")),
                new Product(null, "E-note Tablet 10", "E-ink note-taking tablet", 399.00, true, tablet, List.of("e-ink","note-taking"))
        );

        productRepository.saveAll(products);
    }
}
