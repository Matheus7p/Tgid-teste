package br.com.matheus.application.usecase;

import br.com.matheus.application.dto.CreateProductDTO;
import br.com.matheus.domain.entity.Product;
import br.com.matheus.domain.repository.ProductRepository;

public class CreateProductUseCase {
    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product execute(CreateProductDTO dto){
        productRepository.findByName(dto.name()).ifPresent(product -> {
            throw new IllegalStateException("Product with this name already exists");
        });

        Product newProduct = new Product(
                dto.name(),
                dto.description(),
                dto.price(),
                dto.stockQuantity()
        );

        return productRepository.save(newProduct);
    }
}
