package br.com.matheus.application.dto;

import java.util.List;

public record PerformSaleRequestDTO(
        Long userId,
        List<SaleItemRequestDTO> items
) {
}
