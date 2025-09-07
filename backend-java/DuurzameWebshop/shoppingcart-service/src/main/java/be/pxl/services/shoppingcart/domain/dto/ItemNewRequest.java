package be.pxl.services.shoppingcart.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemNewRequest {
    @Positive
    private long id;
    @Positive
    private long productId;
    @Positive
    private int quantity;
}
