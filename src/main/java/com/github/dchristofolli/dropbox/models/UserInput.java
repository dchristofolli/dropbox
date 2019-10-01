package com.github.dchristofolli.dropbox.models;

import io.swagger.annotations.ApiModelProperty;
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
    @NotNull(message = "{nameNotNull}")
    private String nome;
    @CPF(message = "{invalidCpf}")
    private String cpf;
    @Email(message = "{invalidEmail}")
    private String email;
    private String seguidor;
    @Size(min = 4, message = "{invalidPass}")
    private String senha;

}
