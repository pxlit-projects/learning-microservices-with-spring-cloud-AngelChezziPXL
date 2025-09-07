package be.pxl.services.shoppingcart.service;

import be.pxl.services.shoppingcart.client.ProductClient;
import be.pxl.services.shoppingcart.client.WhishListClient;
import be.pxl.services.shoppingcart.domain.Item;
import be.pxl.services.shoppingcart.domain.ShoppingCart;
import be.pxl.services.shoppingcart.domain.ShoppingCartStatus;
import be.pxl.services.shoppingcart.domain.dto.*;
import be.pxl.services.shoppingcart.domain.factory.IItemFactory;
import be.pxl.services.shoppingcart.exception.ConflictException;
import be.pxl.services.shoppingcart.exception.ResourceNotFoundException;
import be.pxl.services.shoppingcart.repository.IItemRepository;
import be.pxl.services.shoppingcart.repository.IShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShoppingCartService implements IShoppingCartService {

    private final IShoppingCartRepository shoppingCartRepository;
    private final IItemRepository itemRepository;
    private final ProductClient productClient;
    private final IItemFactory itemFactory;
    private final WhishListClient whishListClient;


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
    public ShoppingCartDto removeItemByItemId(long shoppinCartId, long itemId)
    {
        ShoppingCart shoppingCart = getShoppingCartFromDb(shoppinCartId);
        shoppingCart.removeItem(getItemFromDb(itemId));
        return shoppingCartRepository.save(shoppingCart).toShoppingCartDto();
    }


    @Override
    public ShoppingCartDto doOrder(long shoppingCartId) {
        ShoppingCart shoppingCart = getShoppingCartFromDb(shoppingCartId);
        shoppingCart.setStatus(ShoppingCartStatus.ORDER);
        return shoppingCartRepository.save(shoppingCart).toShoppingCartDto();
    }

    @Override
    public ShoppingCartDto checkOut(long shoppingCartId) {
        ShoppingCart shoppingCart = getShoppingCartFromDb(shoppingCartId);
        shoppingCart.setStatus(ShoppingCartStatus.PAYED);
        return shoppingCartRepository.save(shoppingCart).toShoppingCartDto();
    }

    @Override
    public void publishShoppingCartToWhishlist(long userId, long shoppingCartId) {
        ShoppingCart shoppingCart = getShoppingCartFromDb(shoppingCartId);
        whishListClient.publishToWhishList(shoppingCart.toShoppingCartDto().toShoppingCartResponse());
    }

    // PRIVATE HELPER METHODS
    private boolean userHasShoppingCart(long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId).orElse(null);
        return shoppingCart != null;
    }

    private ShoppingCart getShoppingCartFromDb (long id){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElse(null);
        if(shoppingCart != null) {throw new ResourceNotFoundException("Shopping cart with id " + id + " not found.");}
        return shoppingCart;
    }

    private Item getItemFromDb (long id){
        Item item = itemRepository.findById(id).orElse(null);
        if(item == null) {throw new ResourceNotFoundException("Item with id " + id + " not found.");}
        return item;
    }

}
