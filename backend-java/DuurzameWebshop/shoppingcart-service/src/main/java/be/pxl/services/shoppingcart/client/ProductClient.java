package be.pxl.services.shoppingcart.client;

import be.pxl.services.shoppingcart.domain.dto.ProductRequest;
import be.pxl.services.shoppingcart.domain.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="productcatalog-service")
public interface ProductClient {
    @GetMapping("/api/product")
    List<ProductResponse> getAllProducts();
}
