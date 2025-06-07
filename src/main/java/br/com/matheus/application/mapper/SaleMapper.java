package br.com.matheus.application.mapper;

import br.com.matheus.application.dto.SaleItemResponseDTO;
import br.com.matheus.application.dto.SaleResponseDTO;
import br.com.matheus.domain.entity.Sale;
import br.com.matheus.domain.entity.SaleItem;

import java.util.stream.Collectors;

public class SaleMapper {

    public static SaleResponseDTO toResponseDTO(Sale sale){
        var itemsDTO = sale.getItems().stream()
                .map(SaleMapper::toItemResponseDTO)
                .collect(Collectors.toList());

        return new SaleResponseDTO(
                sale.getId(),
                sale.getDateSale(),
                sale.getUser().getName(),
                sale.getTotal(),
                itemsDTO
        );
    }

    private static SaleItemResponseDTO toItemResponseDTO(SaleItem item){
        return new SaleItemResponseDTO(
                item.getProduct().getName(),
                item.getQuantity(),
                item.getUnitPrice()
        );
    }
}
