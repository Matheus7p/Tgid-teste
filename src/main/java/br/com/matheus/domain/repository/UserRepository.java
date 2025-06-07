package br.com.matheus.domain.repository;

import br.com.matheus.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User update(User user);
    void deleteById(Long ig);
    Optional<User> findById(Long id);
    Optional<User> findByCpf(String cpf);

}
