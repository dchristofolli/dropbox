package com.github.dchristofolli.dropbox.v1.user.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String follower;
    private String password;
}
