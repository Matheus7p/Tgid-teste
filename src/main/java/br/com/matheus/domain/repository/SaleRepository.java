package br.com.matheus.domain.repository;

import br.com.matheus.domain.entity.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    Sale save(Sale sale);
    Optional<Sale> findById(Long id);
    List<Sale> findByUser(Long user_id);
}
