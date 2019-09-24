package com.github.dchristofolli.dropbox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

}
