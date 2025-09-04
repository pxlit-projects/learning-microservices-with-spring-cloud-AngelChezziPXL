package be.pxl.services.shoppingcart.domain;

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
    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;
    @ManyToMany
    @JoinTable(name= "shoppingcart_item", joinColumns = @JoinColumn(name= "cart_id"), inverseJoinColumns = @JoinColumn(name= "item_id"))
    private List<Item> items = new ArrayList<>();


    //Methods
    public double calculateTotalAmount() {
        double total = 0;
        for(Item item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void addShoppingCartItem(Item item) {
        if(items.contains(item)) {return;}
        items.add(item);
        item.addShoppingCart(this);
    }

    public void removeShoppingCartItem(Item item) {
        if(!items.contains(item)) {return;}
        items.remove(item);
        item.removeShoppingCart(this);
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
