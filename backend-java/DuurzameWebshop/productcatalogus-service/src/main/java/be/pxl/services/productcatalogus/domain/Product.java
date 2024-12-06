package be.pxl.services.productcatalogus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "category_id")
    private Category category;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    public void setCategory(Category category) {
        this.category = category;
        if (category != null && !category.getProducts().contains(this)) {
            category.getProducts().add(this); // Maintain bidirectional consistency
        }

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
