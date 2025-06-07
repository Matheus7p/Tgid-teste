package br.com.matheus.application.dto;

public record CreateUserDTO(
        String name,
        String cpf,
        String email,
        String password
) {
}
