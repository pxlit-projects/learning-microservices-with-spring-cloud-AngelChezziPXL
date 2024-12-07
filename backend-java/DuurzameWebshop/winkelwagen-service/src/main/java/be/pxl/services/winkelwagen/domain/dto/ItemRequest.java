package be.pxl.services.winkelwagen.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemRequest {
    Long id;
    String name;
    String description;
    double price;
}
