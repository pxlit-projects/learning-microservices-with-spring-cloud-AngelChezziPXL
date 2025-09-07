package be.pxl.services.shoppingcart.service;


import be.pxl.services.shoppingcart.domain.dto.ItemNewRequest;
import be.pxl.services.shoppingcart.domain.dto.ProductResponse;
import be.pxl.services.shoppingcart.domain.dto.ShoppingCartDto;

import java.util.List;

public interface IShoppingCartService {

    List<ProductResponse> getAllProducts();
    ShoppingCartDto createNewShoppingcart(long userId);
    ShoppingCartDto addItemToShoppingcart(long userId, ItemNewRequest itemNewRequest);
    ShoppingCartDto removeItemByItemId(long shoppincartId, long itemId);
    ShoppingCartDto doOrder(long shoppingcartId);
    ShoppingCartDto checkOut(long shoppingCartId);
    void publishShoppingCartToWhishlist(long userId, long shoppingCartId);
}
