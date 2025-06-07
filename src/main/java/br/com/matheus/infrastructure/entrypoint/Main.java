package br.com.matheus.infrastructure.entrypoint;

import br.com.matheus.application.dto.*;
import br.com.matheus.application.usecase.CreateProductUseCase;
import br.com.matheus.application.usecase.CreateUserUseCase;
import br.com.matheus.application.usecase.PerformSaleUseCase;
import br.com.matheus.domain.repository.ProductRepository;
import br.com.matheus.domain.repository.SaleRepository;
import br.com.matheus.domain.repository.UserRepository;
import br.com.matheus.infrastructure.repository.hibernate.ProductRepositoryHibernateImpl;
import br.com.matheus.infrastructure.repository.hibernate.SaleRepositoryHibernateImpl;
import br.com.matheus.infrastructure.repository.hibernate.UserRepositoryHibernateImpl;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- inicio da aplicação ---");

        UserRepository userRepository = new UserRepositoryHibernateImpl();
        ProductRepository productRepository = new ProductRepositoryHibernateImpl();
        SaleRepository saleRepository = new SaleRepositoryHibernateImpl();

        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);
        CreateProductUseCase createProductUseCase = new CreateProductUseCase(productRepository);
        PerformSaleUseCase performSaleUseCase = new PerformSaleUseCase(userRepository, productRepository, saleRepository);

        System.out.println("\n1. cadastrar um usuario e alguns produtos para exemplo");
        createUserUseCase.execute(new CreateUserDTO("Matheus Costa", "777.777.777-77", "matheus@email.com", "12345"));
        createProductUseCase.execute(new CreateProductDTO("Garrafa Growth","Somos todos atletas", new BigDecimal("20.00"), 50));
        createProductUseCase.execute(new CreateProductDTO("Celular Xiaomi note 11", "Celular muito bom, fera demaiss", new BigDecimal("1599.99"), 20));


        System.out.println("\n2. Simulando uma compra");
        PerformSaleRequestDTO saleRequest = new PerformSaleRequestDTO(
                1L,
                List.of(
                        new SaleItemRequestDTO(1L, 2),
                        new SaleItemRequestDTO(2L, 1)
                )
        );

        SaleResponseDTO saleResponse = performSaleUseCase.execute(saleRequest);
        System.out.println("\nSucesso na venda");
        System.out.println("Id da Venda: " + saleResponse.saleId());
        System.out.println("Cliente: " + saleResponse.customerName());
        System.out.println("Data: " + saleResponse.saleDate());
        System.out.println("Valor total: R$ " + saleResponse.totalAmount());
        System.out.println("Itens Comprados:");
        saleResponse.items().forEach(item ->
                System.out.println(String.format("  - %s | Qtd: %d | Preço Un.: R$ %.2f",
                        item.productName(), item.quantity(), item.unitPrice()))
        );

        System.out.println("\nfim da aplicação");
    }
}