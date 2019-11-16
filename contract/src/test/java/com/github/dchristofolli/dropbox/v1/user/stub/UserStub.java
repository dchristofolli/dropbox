package com.github.dchristofolli.dropbox.v1.user.stub;

import com.github.dchristofolli.dropbox.v1.user.repository.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserStub {
    public static UserEntity userEntityStub() {
        return UserEntity.builder()
                .id("13454")
                .cpf("75949011007")
                .email("teste@123.com")
                .follower(null)
                .password("123456")
                .name("Bruce")
                .build();
    }
}
