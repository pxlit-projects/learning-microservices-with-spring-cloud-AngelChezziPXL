package be.pxl.services.shoppingcart.controller;

import be.pxl.services.shoppingcart.domain.dto.ItemNewRequest;
import be.pxl.services.shoppingcart.domain.dto.ProductResponse;
import be.pxl.services.shoppingcart.domain.dto.ShoppingCartDto;
import be.pxl.services.shoppingcart.domain.dto.ShoppingCartResponse;
import be.pxl.services.shoppingcart.exception.AuthorizationException;
import be.pxl.services.shoppingcart.service.IShoppingCartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/shoppingcart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private Logger LOG = LoggerFactory.getLogger(ShoppingCartController.class);
    private final IShoppingCartService shoppingCartService;

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String testEndpoint() {
        return "Shoppingcart service is Ok";
    }

    @GetMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        LOG.info("Fetching all products.");
        return shoppingCartService.getAllProducts();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCartResponse getShoppingCart(@RequestHeader Map<String, String> header) {
        LOG.info("Fetching shoppingcart for current user.");
        checkAuthorization(header);
        long userId = Long.parseLong(header.get("user_id"));
        ShoppingCartDto shoppingCartDto = shoppingCartService.getShoppingCartByUserId(userId);
        return shoppingCartDto.toShoppingCartResponse();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/new")
    public ShoppingCartResponse createShoppingcart(@RequestHeader Map<String, String> headers){
        LOG.info("Creating shoppingcart.");
        checkAuthorization(headers);
        long userId = Long.parseLong(headers.get("user_id"));
        return shoppingCartService.createNewShoppingcart(userId).toShoppingCartResponse();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/item")
    public ShoppingCartResponse addItemToShoppingCart(@RequestHeader Map<String, String> headers, @RequestBody @Valid ItemNewRequest itemNewRequest) {
        LOG.info("Adding new item to shopping cart.");
        checkAuthorization(headers);
        long userId = Long.parseLong(headers.get("user_id"));
        return shoppingCartService.addItemToShoppingcart(userId, itemNewRequest).toShoppingCartResponse();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/item/{shoppingCartId}/{itemId}")
    public ShoppingCartResponse removeItemFromShoppingCart(@RequestHeader Map<String, String> headers, @PathVariable long shoppingCartId, @PathVariable long itemId) {
        LOG.info("Removing item from shopping cart.");
        checkAuthorization(headers);
        ShoppingCartDto shoppingCartDto = shoppingCartService.removeItemByItemId(shoppingCartId, itemId);
        return shoppingCartDto.toShoppingCartResponse();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/order/{shoppingCartId}")
    public ShoppingCartResponse doOrder(@RequestHeader Map<String, String> headers, @PathVariable long shoppingCartId) {
        LOG.info("Conferming shopping cart order.");
        checkAuthorization(headers);
        return shoppingCartService.doOrder(shoppingCartId).toShoppingCartResponse();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/payment/{shoppingCartId}")
    public ShoppingCartResponse payOrder(@RequestHeader Map<String, String> headers, @PathVariable long shoppingCartId) {
        LOG.info("Checking out shopping cart.");
        checkAuthorization(headers);
        return shoppingCartService.checkOut(shoppingCartId).toShoppingCartResponse();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/whishlist/{itemId}")
    public void AddToWhishlist(@RequestHeader Map<String, String> headers, @PathVariable long itemId) {
        LOG.info("Publishing shopping cart to whishlist.");
        checkAuthorization(headers);
        long userId = Long.parseLong(headers.get("user_id"));
        shoppingCartService.publishItemToWhishlist(userId, itemId);
    }

    //PRIVATE HELPER METHODS
    private void checkAuthorization(Map<String, String> headers) {
        LOG.info("Checking authorization.");
        String role = headers.get("role") != null ? headers.get("role") : null;
        long userId = headers.get("user_id") != null ? Long.parseLong(headers.get("user_id")): 0;
//        if (!role.equalsIgnoreCase("admin")) {
//            LOG.debug("You are not authorized to access the logbook");
//            throw new AuthorizationException("You are not allowed to access this resource.");
//        }

        if(userId < 1) {
            LOG.debug("User id cannot be null");
            throw new AuthorizationException("User id cannot be null or zero");
        }
        LOG.info("Authorization successful.");
    }

}
