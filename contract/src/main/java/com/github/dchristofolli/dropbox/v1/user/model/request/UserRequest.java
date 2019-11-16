package com.github.dchristofolli.dropbox.v1.user.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    private String id;
    @Size(min = 3, message = "{nameMinSize}")
    private String name;

    @CPF(message = "{invalidCpf}")
    private String cpf;

    @NotBlank(message = "{emailNotBlank}")
    @Email(message = "{invalidEmail}")
    private String email;

    @Size(min = 4, message = "{invalidPass}")
    private String password;
}
