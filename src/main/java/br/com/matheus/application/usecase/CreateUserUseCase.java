package br.com.matheus.application.usecase;

import br.com.matheus.application.dto.CreateUserDTO;
import br.com.matheus.domain.entity.User;
import br.com.matheus.domain.repository.UserRepository;

public class CreateUserUseCase {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(CreateUserDTO dto) {
        userRepository.findByCpf(dto.cpf()).ifPresent(user -> {
            throw new IllegalStateException("User with this CPF already exists");
        });

        User newUser = new User(
                dto.name(),
                dto.cpf(),
                dto.email(),
                dto.password()
        );
        return userRepository.save(newUser);
    }
}
