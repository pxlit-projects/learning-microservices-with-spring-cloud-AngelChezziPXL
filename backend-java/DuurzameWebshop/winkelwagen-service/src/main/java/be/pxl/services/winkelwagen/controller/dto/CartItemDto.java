package be.pxl.services.winkelwagen.controller.dto;

import be.pxl.services.winkelwagen.domain.CartItem;
import be.pxl.services.winkelwagen.domain.ShoppingCart;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class CartItemDto {

    private Long id;
    private ProductDto productDto;
    private int quantity;
    List<ShoppingCartDto> shoppingCartDtos = new ArrayList<>();

    public static CartItemDto fromCartItem(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setProductDto(ProductDto.fromProduct(cartItem.getProduct()));
        cartItemDto.setQuantity(cartItem.getQuantity());
        List<ShoppingCartDto> shoppingCartDtos = new ArrayList<>();
        for(ShoppingCart cart : cartItem.getCarts()){
            shoppingCartDtos.add(ShoppingCartDto.fromShoppingCart(cart));
        }
        cartItemDto.setShoppingCartDtos(shoppingCartDtos);
        return cartItemDto;
    }
}
