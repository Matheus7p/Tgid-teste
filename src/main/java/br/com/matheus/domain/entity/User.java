package br.com.matheus.domain.entity;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", nullable = false, length = 200)
    private String _cpf;

    @Column(name = "name", nullable = false, unique = true, length = 14)
    private String _name;

    @Column(name = "email", nullable = false, unique =true)
    private String _email;

    @Column(name = "password", nullable = false, length = 255)
    private String _password;

    public User(){}

    public User(String _name, String _cpf, String _email, String _rawPassword){
        this._name = _name;
        this._cpf = _cpf;
        this._email = _email;
        this.set_password(_rawPassword);
    }

    public boolean verifyPassword(String _rawPassword){
        return BCrypt.verifyer().verify(_rawPassword.toCharArray(), this._password).verified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String get_cpf() {
        return _cpf;
    }

    public void set_cpf(String _cpf) {
        this._cpf = _cpf;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _rawPassword) {
        this._password = BCrypt.withDefaults().hashToString(12, _rawPassword.toCharArray());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", _name='" + _name + '\'' +
                ", _email='" + _email + '\'' +
                ", _cpf='" + _cpf + '\'' +
                '}';
    }

}

