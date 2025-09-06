package be.pxl.services.logbook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLogResponse {
    private Long id;
    private LocalDateTime timestamp;
    private Long userId;
    private Long productId;
    private String name;
    private String description;
    private String categoryName;
    private Boolean available;
    private List<String> tags;
    private double price;

}
