package be.pxl.services.shoppingcart.domain.dto;

import be.pxl.services.shoppingcart.domain.ShoppingCartStatus;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Builder
public class ShoppingCartResponse {
    @Min(1)
    private long shoppingCartId;
    @Min(1)
    private long userId;
    private ShoppingCartStatus status;
    private List<ItemResponse> itemResponseList = new ArrayList<>();
}
