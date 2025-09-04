package be.pxl.services.winkelwagen.controller;

import be.pxl.services.winkelwagen.service.dto.ItemDto;
import be.pxl.services.winkelwagen.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/winkelwagen")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/{id}")
    public ItemDto AddItemToShoppingCart(@PathVariable long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
