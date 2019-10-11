package com.github.dchristofolli.dropbox.file.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class FileModelList {
    List<FileModel> files;

    public void add(FileModel ftpFile) {
        files.add(ftpFile);
    }
}
