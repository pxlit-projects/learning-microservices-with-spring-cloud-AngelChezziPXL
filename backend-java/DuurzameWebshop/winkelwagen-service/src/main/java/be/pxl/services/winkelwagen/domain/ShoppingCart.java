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
    @ManyToMany
    @JoinTable(
            name= "shoppingcart_cartitem",
            joinColumns = @JoinColumn(name= "cart_id"),
            inverseJoinColumns = @JoinColumn(name= "item_id")
    )
    private List<CartItem> cartItems = new ArrayList<>();

    public double calculateTotalAmount() {
        double total = 0;
        for(CartItem cartItem : cartItems) {
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    public void addShoppingCartItem(CartItem cartItem) {
        if(cartItems.contains(cartItem)) {return;}
        cartItems.add(cartItem);
        cartItem.addShoppingCart(this);
    }

    public void removeShoppingCartItem(CartItem cartItem) {
        if(!cartItems.contains(cartItem)) {return;}
        cartItems.remove(cartItem);
        cartItem.removeShoppingCart(this);
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
