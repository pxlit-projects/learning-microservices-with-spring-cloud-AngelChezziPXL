package be.pxl.services.whishlist.controller;

import be.pxl.services.whishlist.domain.Whishlist;
import be.pxl.services.whishlist.services.IWishlistService;
import lombok.RequiredArgsConstructor;
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
