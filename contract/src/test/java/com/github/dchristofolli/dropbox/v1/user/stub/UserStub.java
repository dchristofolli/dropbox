package com.github.dchristofolli.dropbox.v1.user.stub;

import com.github.dchristofolli.dropbox.v1.user.repository.UserEntity;

public class UserStub {
    public static UserEntity userEntityStub(){
        return UserEntity.builder()
                .id("13454")
                .name("Daniel")
                .cpf("05553232694")
                .email("daniel@gmail.com")
                .build();
    }
}
