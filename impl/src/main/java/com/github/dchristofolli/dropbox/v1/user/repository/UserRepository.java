package com.github.dchristofolli.dropbox.v1.user.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Repository
public class UserRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserEntity createUser(UserEntity user) {
        String sql = "insert into users (name, cpf, email, password) values (:name, :cpf, :email, :password)";
        var parameters = new MapSqlParameterSource();
        parameters.addValue("name", user.getName());
        parameters.addValue("cpf", user.getCpf());
        parameters.addValue("email", user.getEmail());
        parameters.addValue("password", user.getPassword());

        namedParameterJdbcTemplate.update(sql, parameters);
        return user;
    }
}
