package com.github.dchristofolli.dropbox.v1.user.mapper;

import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import com.github.dchristofolli.dropbox.v1.user.model.request.UserRequest;
import com.github.dchristofolli.dropbox.v1.user.model.response.UserResponse;

public class UserMapperContract {
    public static UserResponse mapToContract(UserModel user) {
        return UserResponse.builder().
                id(user.getId()).
                name(user.getName()).
                build();
    }

    public static UserModel mapToImpl(UserRequest user) {
        return UserModel.builder()
                .name(user.getName())
                .email(user.getEmail())
                .cpf(user.getCpf())
                .password(user.getPassword())
                .build();
    }

}
