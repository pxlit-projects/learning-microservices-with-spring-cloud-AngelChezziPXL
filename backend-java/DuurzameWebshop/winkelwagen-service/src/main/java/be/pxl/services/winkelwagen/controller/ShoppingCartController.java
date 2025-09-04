package be.pxl.services.winkelwagen.controller;

import be.pxl.services.winkelwagen.controller.dto.ItemNewRequest;
import be.pxl.services.winkelwagen.service.ShoppingCartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/shoppingcart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String sayHello() {
        return "Get Ok";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{id}")
    public String AddItemToShoppingCart(@PathVariable Integer id,@Valid @RequestBody ItemNewRequest itemNewRequest) {
        return "Post ok";

    }

}
