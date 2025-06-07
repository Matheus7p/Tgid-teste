package br.com.matheus.domain.repository;

import br.com.matheus.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    void update(User user);
    void deleteById(Long ig);
    Optional<User> findById(Long id);
    Optional<User> findByCpf(String cpf);

}
