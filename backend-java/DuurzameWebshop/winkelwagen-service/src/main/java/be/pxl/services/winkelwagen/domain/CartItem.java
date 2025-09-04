package be.pxl.services.winkelwagen.domain;

import jakarta.persistence.*;
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
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(optional = false)
    private Product product;

    private int quantity;

    public double calculateLineTotal() {
        double lineTotal = product.getPrice() * (double)quantity;
        return Math.round(lineTotal * 100.0) / 100.0;               // will be rounded to 0.01 precision
    }

    @ManyToMany(mappedBy = "cartItems")
    private List<ShoppingCart> carts= new ArrayList<>();

    public void addShoppingCart(ShoppingCart cart) {
        if(carts.contains(cart)) {return;}
        carts.add(cart);
        cart.addShoppingCartItem(this);
    }

    public void removeShoppingCart(ShoppingCart shoppingCart) {
        if(!carts.contains(shoppingCart)) {return;}
        carts.remove(shoppingCart);
        shoppingCart.removeShoppingCartItem(this);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CartItem that)) return false;
        return Double.compare(quantity, that.quantity) == 0 && Objects.equals(id, that.id) && Objects.equals(carts, that.carts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, carts);
    }

}
