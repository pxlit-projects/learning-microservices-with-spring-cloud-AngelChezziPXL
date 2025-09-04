package be.pxl.services.winkelwagen.controller;

import be.pxl.services.winkelwagen.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/winkelwagen")
@AllArgsConstructor
public class ShoppingCartController {
    ShoppingCartService shoppingCartService;

    @PostMapping("/{id}")
    public void AddItemToShoppingCart(@PathVariable long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
