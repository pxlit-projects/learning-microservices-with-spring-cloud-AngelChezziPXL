package be.pxl.services.shoppingcart.domain;

import be.pxl.services.shoppingcart.domain.dto.ItemDto;
import be.pxl.services.shoppingcart.domain.dto.ItemResponse;
import be.pxl.services.shoppingcart.domain.dto.ShoppingCartDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private Long productId;
    @Transient
    private Product product;
    private int quantity;
    @ManyToOne
    private ShoppingCart shoppingCart;


    //Methods
    public double calculateLineTotal() {
        double lineTotal = product.getPrice() * (double)quantity;
        return Math.round(lineTotal * 100.0) / 100.0;               // will be rounded to 0.01 precision
    }

    public ItemDto toItemDto() {
        return ItemDto.builder()
                .id(id)
                .productId(productId)
                .productDto(product.toProductDto())
                .quantity(quantity)
                .shoppingCartDto(shoppingCart.toShoppingCartDto())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item item)) return false;
        return quantity == item.quantity && Objects.equals(id, item.id) && Objects.equals(productId, item.productId) && Objects.equals(product, item.product) && Objects.equals(shoppingCart, item.shoppingCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, product, quantity, shoppingCart);
    }
}
