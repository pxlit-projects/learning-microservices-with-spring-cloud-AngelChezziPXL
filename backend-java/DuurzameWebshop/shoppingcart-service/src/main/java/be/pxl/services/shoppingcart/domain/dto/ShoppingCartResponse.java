package be.pxl.services.shoppingcart.domain.dto;

import be.pxl.services.shoppingcart.domain.ShoppingCartStatus;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartResponse {
    @Min(1)
    private long shoppingCartId;
    @Min(1)
    private long userId;
    private ShoppingCartStatus status;
    private List<ItemResponse> itemResponseList = new ArrayList<>();
}
