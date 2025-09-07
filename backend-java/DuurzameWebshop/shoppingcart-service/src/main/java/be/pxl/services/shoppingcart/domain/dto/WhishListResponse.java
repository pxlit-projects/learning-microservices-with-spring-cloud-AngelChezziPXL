package be.pxl.services.shoppingcart.domain.dto;

import java.util.List;

public record WhishListResponse(long id, long userId, List<ProductDto> productDtoList) {}
