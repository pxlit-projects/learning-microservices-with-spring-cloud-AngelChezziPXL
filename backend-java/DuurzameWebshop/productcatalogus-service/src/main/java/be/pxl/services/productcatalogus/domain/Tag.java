package be.pxl.services.productcatalogus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name="tag")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;

    @ManyToMany(mappedBy="tags")
    private List<Product> products = new ArrayList<>();

    // helper methods
    public void addProduct(Product product) {
        if(products == null) {
            products = new ArrayList<>();
        }
        if (!products.contains(product)) {
            products.add(product);
            product.getTags().add(this);    // Synchronize bidirectional relationship
        }
    }

    public void removeProduct(Product product) {
        if (products.contains(product)) {
            products.remove(product);
            product.getTags().remove(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Reference equality
        if (o == null || getClass() != o.getClass()) return false; // Type check

        Tag tag = (Tag) o;

        // Check equality based on ID if it is set
        return id != null && id.equals(tag.id);
    }

    @Override
    public int hashCode() {
        // Hash based on ID if it is set; otherwise, hash the default identity
        return id != null ? id.hashCode() : super.hashCode();
    }
}

