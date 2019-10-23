package com.github.dchristofolli.dropbox.v1.file.model;

<<<<<<< HEAD
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FileModel {
    String fileName;
    String fileSize;
    String fileData;

=======
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
>>>>>>> c36e740bfccbbe49aeca563585c738fd3efe0afb
}
