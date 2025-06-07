package br.com.matheus.application.usecase;

import br.com.matheus.application.dto.PerformSaleRequestDTO;
import br.com.matheus.application.dto.SaleItemRequestDTO;
import br.com.matheus.application.dto.SaleResponseDTO;
import br.com.matheus.application.mapper.SaleMapper;
import br.com.matheus.domain.entity.Product;
import br.com.matheus.domain.entity.Sale;
import br.com.matheus.domain.entity.SaleItem;
import br.com.matheus.domain.entity.User;
import br.com.matheus.domain.repository.ProductRepository;
import br.com.matheus.domain.repository.SaleRepository;
import br.com.matheus.domain.repository.UserRepository;

public class PerformSaleUseCase {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public PerformSaleUseCase(UserRepository userRepository, ProductRepository productRepository, SaleRepository saleRepository){
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    public SaleResponseDTO execute(PerformSaleRequestDTO requestDTO) {
        User buyer = userRepository.findById(requestDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + requestDTO.userId()));

        Sale newSale = new Sale(buyer);

        for (SaleItemRequestDTO itemDTO : requestDTO.items()){
            Product product = productRepository.findById(itemDTO.productId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with id: "+ itemDTO.productId()));

            if(product.getStockQuantity() < itemDTO.quantity()){
                throw new IllegalStateException("Not enough stock for product: " + product.getName());
            }

            int newStock = product.getStockQuantity() - itemDTO.quantity();
            product.setStockQuantity(newStock);

            SaleItem saleItem = new SaleItem(newSale, product, itemDTO.quantity());
            newSale.getItems().add(saleItem);
        }
        newSale.getTotal();
        Sale savedSale = saleRepository.save(newSale);
        return SaleMapper.toResponseDTO(savedSale);
    }
}
