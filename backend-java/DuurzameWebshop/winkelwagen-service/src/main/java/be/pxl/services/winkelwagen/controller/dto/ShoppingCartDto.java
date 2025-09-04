package be.pxl.services.winkelwagen.controller.dto;

import be.pxl.services.winkelwagen.domain.CartItem;
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
    private List<CartItemDto> cartItemDtos = new ArrayList<>();

    public double calculateTotalAmount(){
        double total = 0;
        for (CartItemDto cartItemDto : cartItemDtos) {
            total += cartItemDto.getQuantity() * cartItemDto.getProductDto().getPrice();
        }
        return total;
    }

    public static ShoppingCartDto fromShoppingCart(ShoppingCart shoppingCart) {
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setId(shoppingCart.getId());
        dto.setUserId(shoppingCart.getUserId());
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            CartItemDto cartItemDto = CartItemDto.fromCartItem(cartItem);
            cartItemDtos.add(cartItemDto);
        }
        dto.setCartItemDtos(cartItemDtos);
        return dto;
    }
}
