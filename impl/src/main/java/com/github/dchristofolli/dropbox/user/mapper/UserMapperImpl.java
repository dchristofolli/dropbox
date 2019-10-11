package com.github.dchristofolli.dropbox.user.mapper;

import com.github.dchristofolli.dropbox.user.model.UserModel;
import com.github.dchristofolli.dropbox.user.repository.UserEntity;


public class UserMapperImpl {
    public static UserModel mapToModel(UserEntity userEntity){
        return UserModel.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .cpf(userEntity.getCpf())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .follower(userEntity.getFollower())
                .build();
    }

    public static UserEntity mapToEntity(UserModel userModel){
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
