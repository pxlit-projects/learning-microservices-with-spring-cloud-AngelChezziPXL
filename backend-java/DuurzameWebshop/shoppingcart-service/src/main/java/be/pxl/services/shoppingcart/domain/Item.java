package be.pxl.services.shoppingcart.domain;

import be.pxl.services.shoppingcart.domain.dto.ItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String name;
    private String description;
    private Double price;
    private int quantity;
    @ManyToOne
    private ShoppingCart shoppingCart;


    //Methods
    public double calculateLineTotal() {
        double lineTotal = price * (double)quantity;
        return Math.round(lineTotal * 100.0) / 100.0;               // will be rounded to 0.01 precision
    }
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public ItemDto toItemDto() {
        return ItemDto.builder()
                .id(id)
                .productId(productId)
                .name(name)
                .quantity(quantity)
                .shoppingCartDto(shoppingCart.toShoppingCartDto())
                .build();
    }
}
