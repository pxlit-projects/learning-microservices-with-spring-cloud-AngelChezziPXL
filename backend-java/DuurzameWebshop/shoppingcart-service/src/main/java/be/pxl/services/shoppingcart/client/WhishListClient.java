package be.pxl.services.shoppingcart.client;

import be.pxl.services.shoppingcart.domain.dto.WhishListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("wishlist-service")
public interface WhishListClient {
    @PostMapping("/api/whishlist")
    WhishListResponse publishToWhishList();
}
