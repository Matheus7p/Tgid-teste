package br.com.matheus.domain.entity;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "email", nullable = false, unique =true)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    public User(){}

    public User(String name, String cpf, String email, String rawPassword){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.setPassword(rawPassword);
    }

    public boolean verifyPassword(String rawPassword){
        return BCrypt.verifyer().verify(rawPassword.toCharArray(), this.password).verified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", _name='" + getName() + '\'' +
                ", _email='" + getEmail() + '\'' +
                ", _cpf='" + getCpf() + '\'' +
                '}';
    }

}

