package be.pxl.services.productcatalogus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    // Helper method to add a product to the category
    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
            product.setCategory(this); // Maintain bidirectional consistency
        }
    }

    // Helper method to remove a product from the category
    public void removeProduct(Product product) {
        if (products.contains(product)) {
            products.remove(product);
            product.setCategory(null); // Break bidirectional relationship
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Reference equality
        if (o == null || getClass() != o.getClass()) return false; // Type check

        Category category = (Category) o;

        // Check equality based on ID if it is set
        return id != null && id.equals(category.id);
    }

    @Override
    public int hashCode() {
        // Hash based on ID if it is set; otherwise, hash the default identity
        return id != null ? id.hashCode() : super.hashCode();
    }
}
