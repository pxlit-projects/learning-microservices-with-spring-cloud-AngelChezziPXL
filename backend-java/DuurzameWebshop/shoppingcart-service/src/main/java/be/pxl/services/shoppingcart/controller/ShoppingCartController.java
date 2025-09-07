package be.pxl.services.shoppingcart.controller;

import be.pxl.services.shoppingcart.domain.dto.ItemNewRequest;
import be.pxl.services.shoppingcart.domain.dto.ItemResponse;
import be.pxl.services.shoppingcart.domain.dto.NewShoppingCartResponse;
import be.pxl.services.shoppingcart.domain.dto.ProductResponse;
import be.pxl.services.shoppingcart.domain.dto.ShoppingCartResponse;
import be.pxl.services.shoppingcart.exception.AuthorizationException;
import be.pxl.services.shoppingcart.service.IShoppingCartService;
import jakarta.validation.Valid;
import jakarta.ws.rs.PathParam;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        LOG.info("Fetching all products.");
        return shoppingCartService.getAllProducts();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/new")
    public ShoppingCartResponse createShoppingcart(@RequestHeader Map<String, String> headers){
        LOG.info("Creating shoppingcart.");
        checkAuthorization(headers);
        long userId = Long.parseLong(headers.get("user_id"));
        return shoppingCartService.createNewShoppingcart(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void AddItemToShoppingCart(@RequestHeader Map<String, String> headers, @PathVariable long shoppingCartId, @RequestBody @Valid ItemNewRequest itemNewRequest) {
        LOG.info("Adding new item to shopping cart.");
        checkAuthorization(headers);
        long userId = Long.parseLong(headers.get("user_id"));
        shoppingCartService.addItemToShoppingCart(userId, shoppingCartId, itemNewRequest);
    }

    //PRIVATE HELPER METHODS
    private void checkAuthorization(Map<String, String> headers) {
        String role = headers.get("role");
        long userId = headers.get("user_id") != null ? Long.parseLong(headers.get("user_id")): 0;
        if (!role.equalsIgnoreCase("admin")) {
            LOG.debug("You are not authorized to access the logbook");
            throw new AuthorizationException("You are not allowed to access this resource.");
        }

        if(userId < 1) {
            LOG.debug("User id cannot be null");
            throw new AuthorizationException("User id cannot be null or zero");
        }
    }

}
