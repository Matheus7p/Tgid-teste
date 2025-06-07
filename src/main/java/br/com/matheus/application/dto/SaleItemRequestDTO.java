package br.com.matheus.application.dto;

public record SaleItemRequestDTO(
        Long productId,
        int quantity
) {
}
