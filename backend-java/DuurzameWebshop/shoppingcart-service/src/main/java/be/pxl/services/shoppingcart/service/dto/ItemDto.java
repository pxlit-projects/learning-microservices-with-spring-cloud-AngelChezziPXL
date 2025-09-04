package be.pxl.services.shoppingcart.service.dto;

import be.pxl.services.shoppingcart.domain.Item;
import be.pxl.services.shoppingcart.domain.ShoppingCart;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class ItemDto {

    private Long id;
    private Long productId;
    private ProductDto productDto;
    private int quantity;
    List<ShoppingCartDto> shoppingCartDtos = new ArrayList<>();

    public static ItemDto fromCartItem(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setProductId(item.getProductId());
        itemDto.setProductDto(ProductDto.fromProduct(item.getProduct()));
        itemDto.setQuantity(item.getQuantity());
        List<ShoppingCartDto> shoppingCartDtos = new ArrayList<>();
        for(ShoppingCart cart : item.getCarts()){
            shoppingCartDtos.add(ShoppingCartDto.fromShoppingCart(cart));
        }
        itemDto.setShoppingCartDtos(shoppingCartDtos);
        return itemDto;
    }

    public Item toCartItem() {
        Item item = new Item();
        item.setId(id);
        item.setProductId(productId);
        item.setProduct(ProductDto.toProduct(productDto));
        item.setQuantity(quantity);
        List<ShoppingCart> carts = new ArrayList<>();
        for(ShoppingCartDto cartDto : shoppingCartDtos){
            carts.add(cartDto.ToShoppingCart());
        }
        return item;
    }
}
