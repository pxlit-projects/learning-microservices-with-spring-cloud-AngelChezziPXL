package be.pxl.services.winkelwagen.service.dto;

import be.pxl.services.winkelwagen.domain.Item;
import be.pxl.services.winkelwagen.domain.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
    private Long id;
    private long userId;
    private List<ItemDto> itemDtos = new ArrayList<>();


    public double calculateTotalAmount(){
        double total = 0;
        for (ItemDto itemDto : itemDtos) {
            total += itemDto.getQuantity() * itemDto.getProductDto().getPrice();
        }
        return total;
    }

    public static ShoppingCartDto fromShoppingCart(ShoppingCart shoppingCart) {
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setId(shoppingCart.getId());
        dto.setUserId(shoppingCart.getUserId());

        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : shoppingCart.getItems()) {
            ItemDto itemDto = ItemDto.fromCartItem(item);
            itemDtos.add(itemDto);
        }
        dto.setItemDtos(itemDtos);

        return dto;
    }

    public ShoppingCart ToShoppingCart() {
        List<Item> items = new ArrayList<>();
        for (ItemDto itemDto : this.itemDtos) {
            items.add(itemDto.toCartItem());
        }

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .id(this.id)
                .userId(this.userId)
                .items(items)
                .build();
        return shoppingCart;
    }
}
