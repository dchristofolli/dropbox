package com.github.dchristofolli.dropbox.v1.file.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileModel {
    String fileName;
    String fileSize;
    String data;
}
