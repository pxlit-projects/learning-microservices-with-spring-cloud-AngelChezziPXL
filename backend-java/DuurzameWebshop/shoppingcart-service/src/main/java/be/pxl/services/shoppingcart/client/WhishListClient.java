package be.pxl.services.shoppingcart.client;

import be.pxl.services.shoppingcart.domain.dto.ItemDto;
import be.pxl.services.shoppingcart.domain.dto.ShoppingCartResponse;
import be.pxl.services.shoppingcart.domain.dto.WhishListDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "whishlist-service", path = "/api/whishlist")
public interface WhishListClient {
    @PostMapping
    void publishToWhishList(long userId, @RequestBody ItemDto itemDto);
}
