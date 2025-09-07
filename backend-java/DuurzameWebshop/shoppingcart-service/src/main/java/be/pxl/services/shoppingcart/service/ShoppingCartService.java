package be.pxl.services.shoppingcart.service;

import be.pxl.services.shoppingcart.client.ProductClient;
import be.pxl.services.shoppingcart.domain.Item;
import be.pxl.services.shoppingcart.domain.ShoppingCart;
import be.pxl.services.shoppingcart.domain.dto.*;
import be.pxl.services.shoppingcart.domain.factory.IItemFactory;
import be.pxl.services.shoppingcart.exception.ConflictException;
import be.pxl.services.shoppingcart.exception.ResourceNotFoundException;
import be.pxl.services.shoppingcart.repository.IShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShoppingCartService implements IShoppingCartService {

    private final IShoppingCartRepository shoppingCartRepository;
    private final ProductClient productClient;
    private final IItemFactory itemFactory;


    @Override
    public List<ProductResponse> getAllProducts() {
        return productClient.getAllProducts();
    }

    @Override
    public ShoppingCartDto createNewShoppingcart(long userId) {
        ShoppingCart newShoppinCart = ShoppingCart.createNew(userId);
        if (userHasShoppingCart(userId)) { throw new ConflictException("User already has shopping cart."); }
        return shoppingCartRepository.save(newShoppinCart).toShoppingCartDto();
    }


    @Override
    public ShoppingCartDto addItemToShoppingcart(long userId, ItemNewRequest itemNewRequest) {
        ShoppingCart shoppingCart = getShoppingCartFromDb(itemNewRequest.getShoppingCartId());
        Item newItem = itemFactory.createItem(userId, itemNewRequest.getProductId(), shoppingCart, itemNewRequest.getName(),itemNewRequest.getDescription(),itemNewRequest.getPrice(), itemNewRequest.getQuantity());
        shoppingCart.addItem(newItem);
        return shoppingCartRepository.save(shoppingCart).toShoppingCartDto();
    }

    @Override
    public ShoppingCartDto removeItemByItemId(Long itemId) {
        throw new NotImplementedException();
    }

    @Override
    public void publishShoppingCartToWishList() {
        throw new NotImplementedException();
    }

    @Override
    public void doOrder() {
        throw new NotImplementedException();
    }

    private boolean userHasShoppingCart(long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId).orElse(null);
        return shoppingCart == null ? false : true;
    }

    private ShoppingCart getShoppingCartFromDb (long id){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElse(null);
        if(shoppingCart != null) {throw new ResourceNotFoundException("Shopping cart with id " + id + " not found.");}
        return shoppingCart;
    }

}
