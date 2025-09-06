package be.pxl.services.productcatalog.domain;

import be.pxl.services.productcatalog.domain.dto.ProductRequest;
import be.pxl.services.productcatalog.domain.dto.ProductResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean available = true;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    public void setCategory(Category category) {
        this.category = category;
        if (category != null && !category.getProducts().contains(this)) {
            category.addProduct(this); // Maintain bidirectional consistency
        }
    }

    public void addTags(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    public ProductResponse toProductResponse() {
        return ProductResponse.builder()
                .id(id)
                .name(name)
                .description(description)
                .available(available)
                .categoryName(category.getName())
                .tags(tags)
                .price(price)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Reference equality
        if (o == null || getClass() != o.getClass()) return false; // Type check

        Product product = (Product) o;

        // Check equality based on ID if it is set
        return id != null && id.equals(product.id);
    }

    @Override
    public int hashCode() {
        // Hash based on ID if it is set; otherwise, hash the default identity
        return id != null ? id.hashCode() : super.hashCode();
    }
}
