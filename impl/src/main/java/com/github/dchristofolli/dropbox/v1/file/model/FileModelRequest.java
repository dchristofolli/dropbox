package com.github.dchristofolli.dropbox.v1.file.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileModelRequest {
    @NotNull(message = "{pageNotNull}")
    private Integer page;
    @NotNull(message = "{quantityNotNull}")
    private Integer quantity;
    @NotNull(message = "{userNotNull}")
    private UserModel userModel;
}
