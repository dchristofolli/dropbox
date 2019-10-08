package com.github.dchristofolli.impl.contract.v1.user.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    @Min(3)
    private String name;

    @CPF(message = "{invalidCpf}")
    private String cpf;

    @Email(message = "{invalidEmail}")
    private String email;

    @Size(min = 4, message = "{invalidPass}")
    private String password;
}
