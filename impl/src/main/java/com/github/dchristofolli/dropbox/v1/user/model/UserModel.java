package com.github.dchristofolli.dropbox.v1.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String follower;
    private String password;

}
