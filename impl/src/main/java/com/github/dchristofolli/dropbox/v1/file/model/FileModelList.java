package com.github.dchristofolli.dropbox.v1.file.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class FileModelList {
    List<FileMapper> files;

    public void add(FileMapper ftpFile) {
        files.add(ftpFile);
    }
}
