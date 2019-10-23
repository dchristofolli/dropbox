package com.github.dchristofolli.dropbox.v1.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class UserModelQueryParamRequest {
    private String id;
    private String cpf;
    private String name;
    private String email;
}
