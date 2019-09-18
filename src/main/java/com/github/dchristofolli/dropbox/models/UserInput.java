package com.github.dchristofolli.dropbox.models;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Service
@Document
public class UserInput {
    @Id
    private String id;
    @NotNull(message = "O nome deve ser preenchido")
    private String nome;
    @CPF(message = "CPF inválido")
    private String cpf;
    @Email(message = "E-mail inválido")
    private String email;
    private String seguidor;
    @NotBlank(message = "A senha deve ser preenchida")
    @Size(min = 4, message = "A senha deve ter pelo menos 4 caracteres")
    private String senha;

    public UserInput() {
    }

    public UserInput(String id, @NotNull(message = "O nome deve ser preenchido") String nome,
                     @CPF(message = "CPF inválido") String cpf,
                     @Email(message = "E-mail inválido") String email, String seguidor,
                     @NotBlank(message = "A senha deve ser preenchida")
                     @Size(min = 4, message = "A senha deve ter pelo menos 4 caracteres") String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.seguidor = seguidor;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(String seguidor) {
        this.seguidor = seguidor;
    }

    @Override
    public boolean equals(Object obj) {
        UserInput user = (UserInput) obj;
        return this.email.equals(user.getEmail())
                || this.id.equals(user.getId());
    }
}
