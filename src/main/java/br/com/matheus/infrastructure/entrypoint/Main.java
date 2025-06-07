package br.com.matheus.infrastructure.entrypoint;


import br.com.matheus.domain.entity.User;
import br.com.matheus.domain.repository.UserRepository;
import br.com.matheus.infrastructure.configuration.HibernateUtil;
import br.com.matheus.infrastructure.repository.hibernate.UserRepositoryHibernateImpl;

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

    }
}