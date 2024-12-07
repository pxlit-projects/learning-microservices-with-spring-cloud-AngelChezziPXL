package be.pxl.services.winkelwagen.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long userId;
    private double totalAmount;
    @ManyToMany
    @JoinTable(
            name= "shopping_cart_item",
            joinColumns = @JoinColumn(name= "cart_id"),
            inverseJoinColumns = @JoinColumn(name= "item_id")
    )
    private List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    public void addItemToCart(ShoppingCartItem shoppingCartItem) {
        shoppingCartItems.add(shoppingCartItem);
    }

    public void removeItemFromCart(ShoppingCartItem shoppingCartItem) {
        shoppingCartItems.remove(shoppingCartItem);
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ShoppingCart that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
