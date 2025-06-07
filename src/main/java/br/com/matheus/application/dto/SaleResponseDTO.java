package br.com.matheus.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record SaleResponseDTO(
        Long saleId,
        LocalDateTime saleDate,
        String customerName,
        BigDecimal totalAmount,
        List<SaleItemResponseDTO> items
) {
}
