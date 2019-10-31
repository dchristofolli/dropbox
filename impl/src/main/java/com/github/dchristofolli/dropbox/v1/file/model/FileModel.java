package com.github.dchristofolli.dropbox.v1.file.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FileModel {
    String fileName;
    String fileSize;
    String fileData;
}
