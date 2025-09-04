package be.pxl.services.controller;

import be.pxl.services.domain.Whishlist;
import be.pxl.services.services.IWishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/whishlist")
public class WhishlistController {

    private IWishlistService wishlistService;

    @GetMapping
    public List<Whishlist> getWhishlist() {
        return wishlistService.getAllWhishlist();
    }
}
