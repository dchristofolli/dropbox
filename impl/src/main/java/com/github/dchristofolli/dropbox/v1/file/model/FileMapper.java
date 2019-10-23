package com.github.dchristofolli.dropbox.v1.file.model;

<<<<<<< HEAD
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {
    public FileModel fileMapper(FTPFile file){
        return FileModel.builder()
                .fileName(file.getName())
                .fileSize(file.getSize() + " kb")
                .fileData(file.getRawListing())
                .build();

=======
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
>>>>>>> c36e740bfccbbe49aeca563585c738fd3efe0afb
    }
}
