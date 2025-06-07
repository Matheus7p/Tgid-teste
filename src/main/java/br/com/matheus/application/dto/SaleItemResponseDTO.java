package br.com.matheus.application.dto;

import java.math.BigDecimal;

public record SaleItemResponseDTO(
        String productName,
        int quantity,
        BigDecimal unitPrice
) {
}
