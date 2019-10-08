package com.github.dchristofolli.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserInput {

    @Id
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String follower;
    private String password;
}
