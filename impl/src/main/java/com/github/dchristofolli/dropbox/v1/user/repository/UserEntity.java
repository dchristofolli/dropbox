package com.github.dchristofolli.dropbox.v1.user.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String follower;
    private String password;
}
