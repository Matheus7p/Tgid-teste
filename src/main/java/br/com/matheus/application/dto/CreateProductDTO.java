package br.com.matheus.application.dto;

import java.math.BigDecimal;

public record CreateProductDTO(
        String name,
        String description,
        BigDecimal price,
        int stockQuantity
) {
}
