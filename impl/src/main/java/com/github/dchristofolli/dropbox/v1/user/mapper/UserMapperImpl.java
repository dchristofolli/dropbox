package com.github.dchristofolli.dropbox.v1.user.mapper;

import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import com.github.dchristofolli.dropbox.v1.user.repository.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapperImpl {
    public static UserModel mapToModel(UserEntity userEntity) {
        return UserModel.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .cpf(userEntity.getCpf())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .follower(userEntity.getFollower())
                .build();
    }

    public static UserEntity mapToEntity(UserModel userModel) {
        return UserEntity.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .cpf(userModel.getCpf())
                .email(userModel.getEmail())
                .password(userModel.getPassword())
                .follower(userModel.getFollower())
                .build();
    }
}
