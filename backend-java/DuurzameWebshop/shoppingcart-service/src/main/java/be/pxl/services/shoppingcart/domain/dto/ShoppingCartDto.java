package be.pxl.services.shoppingcart.domain.dto;

import be.pxl.services.shoppingcart.domain.ShoppingCart;
import be.pxl.services.shoppingcart.domain.ShoppingCartStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartDto {
    private Long id;
    private long userId;
    private ShoppingCartStatus status;
    private List<ItemDto> itemDtos = new ArrayList<>();


    public double calculateTotalAmount(){
        double total = 0;
        for (ItemDto itemDto : itemDtos) {
            total += itemDto.getQuantity() * itemDto.getPrice();
        }
        return total;
    }

    public ShoppingCart toShoppingCart() {
        return ShoppingCart.builder()
                .id(this.id)
                .userId(this.userId)
                .status(this.status)
                .items(itemDtos.stream().map(ItemDto::toItem).collect(Collectors.toList()))
                .build();
    }


}
