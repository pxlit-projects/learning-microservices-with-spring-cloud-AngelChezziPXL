package be.pxl.services.productcatalogus.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private BigDecimal price;
    private boolean available = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    public void setCategory(Category category) {
        this.category = category;
        if (category != null && !category.getProducts().contains(this)) {
            category.getProducts().add(this); // Maintain bidirectional consistency
        }
    }

    // helper methods
    public void addTag(Tag tag) {
        if(this.tags == null){
            this.tags = new ArrayList<>();
        }
        if (!tags.contains(tag)) {
            tags.add(tag);
            tag.getProducts().add(this);    // Synchronize bidirectional relationship
        }
    }

    public void removeTag(Tag tag) {
        if (tags.contains(tag)) {
            tags.remove(tag);
            tag.getProducts().remove(this);
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
