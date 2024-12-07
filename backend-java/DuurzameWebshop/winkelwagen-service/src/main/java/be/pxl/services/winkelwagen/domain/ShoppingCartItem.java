package be.pxl.services.winkelwagen.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItem {
    @Id
    private Long id;
    private String productName;
    private String description;
    private double price;
    private double quantity;
    @ManyToMany(mappedBy = "items")
    private List<ShoppingCart> carts= new ArrayList<>();

    public void add(ShoppingCartItem otherItem) {
        this.quantity += otherItem.quantity;
    }

    public void subtract(ShoppingCartItem otherItem) {
        this.quantity -= otherItem.quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ShoppingCartItem that)) return false;
        return Double.compare(quantity, that.quantity) == 0 && Objects.equals(id, that.id) && Objects.equals(carts, that.carts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, carts);
    }
}
