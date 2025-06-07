package br.com.matheus.domain.repository;

import br.com.matheus.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void save(Product product);
    void update(Product product);
    void deleteById(Long id);
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String product_name);
    List<Product> findAllProduct();
}
