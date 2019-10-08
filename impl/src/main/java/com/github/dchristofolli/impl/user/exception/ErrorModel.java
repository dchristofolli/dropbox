package com.github.dchristofolli.user.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ErrorModel { //TODO complementar com informações api best practices
    private String message;
    private String error;
    private HttpStatus status;
}
