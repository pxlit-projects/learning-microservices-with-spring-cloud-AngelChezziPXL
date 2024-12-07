package be.pxl.services.winkelwagen.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingCartItemRequest {
    Long id;
    String productName;
    String description;
    double price;
    int quantity;
}
