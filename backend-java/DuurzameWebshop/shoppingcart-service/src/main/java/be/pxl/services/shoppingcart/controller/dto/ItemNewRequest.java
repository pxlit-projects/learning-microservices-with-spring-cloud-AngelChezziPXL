package be.pxl.services.shoppingcart.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemNewRequest {
    @Positive
    private long id;
    @NotNull
    private long productId;
    private int quantity;
    @NotBlank
    private String description;

}
