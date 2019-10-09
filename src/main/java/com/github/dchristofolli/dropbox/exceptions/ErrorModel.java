package com.github.dchristofolli.dropbox.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Builder
@AllArgsConstructor
@Data
public class ErrorModel {
    String message;
    String error;
    HttpStatus status;
}
