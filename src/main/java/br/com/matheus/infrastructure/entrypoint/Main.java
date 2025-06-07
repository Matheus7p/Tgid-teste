package br.com.matheus.infrastructure.entrypoint;


import br.com.matheus.application.dto.CreateProductDTO;
import br.com.matheus.application.usecase.CreateProductUseCase;
import br.com.matheus.domain.entity.Product;
import br.com.matheus.domain.entity.User;
import br.com.matheus.domain.repository.ProductRepository;
import br.com.matheus.domain.repository.UserRepository;
import br.com.matheus.infrastructure.configuration.HibernateUtil;
import br.com.matheus.infrastructure.repository.hibernate.ProductRepositoryHibernateImpl;
import br.com.matheus.infrastructure.repository.hibernate.UserRepositoryHibernateImpl;

import java.math.BigDecimal;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserRepository ur = new UserRepositoryHibernateImpl();

        User newUser = new User("fofo", "123", "fofo@gmail.com", "senhaForte123");

        ur.save(newUser);

        Optional<User> foundUser = ur.findByCpf("123");

        foundUser.ifPresent(user -> {
            System.out.println("Usuario encontrado: " + user.toString());
        });

        System.out.println("\n--- INICIANDO TESTE DO CASO DE USO DE PRODUTO ---");


        ProductRepository productRepository = new ProductRepositoryHibernateImpl();
        CreateProductUseCase createProductUseCase = new CreateProductUseCase(productRepository);


        CreateProductDTO productData = new CreateProductDTO("Monitor Ultrawide 34'", "pc foda", new BigDecimal("3250.00"), 30);

        System.out.println("\n3. Executando caso de uso para criar um novo produto...");
        try {
            Product createdProduct = createProductUseCase.execute(productData);
            System.out.println("SUCESSO! Produto criado: " + createdProduct);
        } catch (IllegalStateException e) {
            System.err.println("ERRO: " + e.getMessage());
        }


        System.out.println("\n4. Tentando criar o mesmo produto novamente...");
        try {
            createProductUseCase.execute(productData);
        } catch (IllegalStateException e) {
            System.err.println("ERRO (ESPERADO): " + e.getMessage());
        }

    }
}