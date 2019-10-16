package com.github.dchristofolli.dropbox.v1.file.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.net.ftp.FTPFile;

@Data
@Builder
@AllArgsConstructor
public class FileMapper {
    // TODO terminar de fazer o filemapper
    String fileName;
    String fileSize;
    String data;

    public FileMapper(FTPFile ftpFile) {
        FileMapper.builder()
                .fileName(ftpFile.getName())
                .fileSize(ftpFile.getSize() + "kb")
                .data(ftpFile.getRawListing())
                .build();
    }
}
