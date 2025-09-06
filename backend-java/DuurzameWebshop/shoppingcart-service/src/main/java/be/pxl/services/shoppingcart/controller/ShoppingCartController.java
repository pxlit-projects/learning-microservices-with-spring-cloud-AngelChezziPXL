package be.pxl.services.shoppingcart.controller;

import be.pxl.services.shoppingcart.domain.dto.ItemNewRequest;
import be.pxl.services.shoppingcart.domain.dto.ItemResponse;
import be.pxl.services.shoppingcart.service.IShoppingCartService;
import be.pxl.services.shoppingcart.service.ShoppingCartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/shoppingcart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final IShoppingCartService shoppingCartService;

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String testEndpoint() {
        return "Shoppingcart service is Ok";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemResponse> getAllItemsWithProductDetails() {
        return shoppingCartService.getAllItemsWithProductDetails();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public String AddItemToShoppingCart(@PathVariable Integer id, @RequestBody @Valid ItemNewRequest itemNewRequest) {
        return "Post ok";
    }

}
