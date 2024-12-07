package be.pxl.services.winkelwagen.controller;

import be.pxl.services.winkelwagen.domain.dto.ItemRequest;
import be.pxl.services.winkelwagen.service.IShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/winkelwagen")
@AllArgsConstructor
public class ShoppingCartController {
    IShoppingCartService shoppingCartService;

    @PostMapping
    public void AddItemToShoppingCart(Long userId, ItemRequest itemRequest) {}

}
