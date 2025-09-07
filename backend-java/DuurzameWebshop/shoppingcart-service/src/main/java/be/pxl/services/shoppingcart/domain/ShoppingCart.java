package be.pxl.services.shoppingcart.domain;

import be.pxl.services.shoppingcart.domain.dto.ShoppingCartDto;
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
    @OneToMany(mappedBy = "shoppingcart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    //Methods
    public double calculateTotalAmount() {
        double total = 0;
        for(Item item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public void addItem(Item item) {
        if(!items.contains(item)) {
            items.add(item);
            item.setShoppingCart(this);
        } else {
            item.addQuantity(item.getQuantity());
        }
    }

    public ShoppingCartDto toShoppingCartDto() {
        return ShoppingCartDto.builder()
                .id(id)
                .userId(userId)
                .status(status)
                .itemDtos(items.stream().map(Item::toItemDto).toList())
                .build();
    }

    public static ShoppingCart createNew(long userId) {
        return ShoppingCart.builder()
                .userId(userId)
                .status(ShoppingCartStatus.ACTIVE)
                .items(new ArrayList<>())
                .build();
    }
}
