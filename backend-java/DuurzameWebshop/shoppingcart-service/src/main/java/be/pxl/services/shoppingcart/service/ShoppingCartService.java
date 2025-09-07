package be.pxl.services.shoppingcart.service;

import be.pxl.services.shoppingcart.client.ProductClient;
import be.pxl.services.shoppingcart.domain.ShoppingCart;
import be.pxl.services.shoppingcart.domain.dto.*;
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


    @Override
    public List<ProductResponse> getAllProducts() {
        return productClient.getAllProducts();
    }

    @Override
    public ShoppingCartDto createNewShoppingcart(long userId) {
        ShoppingCart newShoppinCart =  ShoppingCart.createNewShoppingcart(userId);
        if (userHasShoppingCart(userId)) { throw new ConflictException("User already has shopping cart."); }
        return shoppingCartRepository.save(newShoppinCart).toShoppingCartDto();
    }


    @Override
    public ShoppingCartDto addItemToShoppingcart(long userId, long shoppingcartId, ItemNewRequest itemNewRequest) {
        ShoppingCart shoppingCart = getShoppingCartFromDb(shoppingcartId);
        throw new NotImplementedException("Not implemented yet");
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
