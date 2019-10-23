package com.github.dchristofolli.dropbox.v1.file.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FileModel {
    String fileName;
    String fileSize;
    String fileData;

}
