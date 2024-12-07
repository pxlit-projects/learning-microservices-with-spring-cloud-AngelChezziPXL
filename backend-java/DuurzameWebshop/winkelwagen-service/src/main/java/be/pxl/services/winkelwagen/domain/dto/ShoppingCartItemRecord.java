package be.pxl.services.winkelwagen.domain.dto;

public record ShoppingCartItemRecord(Long id, int quantity, String productName, double price) {}
