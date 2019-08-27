package com.github.dchristofolli.dropbox.controllers;

import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private String id;
    @NotNull(message = "O nome deve ser preenchido")
    private String nome;
    @CPF (message = "CPF inválido")
    private String cpf;
    @Email (message = "E-mail inválido")
    private String email;
    private Integer seguidores;
    @NotBlank(message = "A senha deve ser preenchida")
    private String senha;

    public User(){

    }

    public User(String id, @NotNull(message = "O nome deve ser preenchido") String nome,
                @CPF(message = "CPF inválido") String cpf, @Email(message = "E-mail inválido") String email,
                Integer seguidores,
                @NotBlank(message = "A senha deve ser preenchida") String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.seguidores = seguidores;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Integer seguidores) {
        this.seguidores = seguidores;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
