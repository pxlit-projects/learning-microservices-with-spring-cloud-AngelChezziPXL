package be.pxl.services.winkelwagen.service;

import be.pxl.services.winkelwagen.controller.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
}
