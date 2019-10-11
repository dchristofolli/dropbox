package com.github.dchristofolli.dropbox.v1.user.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    @JsonProperty(value="name")
    @NotNull(message = "{nameNotNull}")
    private String name;

    @JsonProperty("cpf")
    @CPF(message = "{invalidCpf}")
    private String cpf;


    @JsonProperty("email")
    @Email(message = "{invalidEmail}")
    private String email;

    @JsonProperty("password")
    @Size(min = 4, message = "{invalidPass}")
    private String password;
}
