package com.github.dchristofolli.dropbox.v1.file;

import com.github.dchristofolli.dropbox.v1.file.model.FileMapper;
import com.github.dchristofolli.dropbox.v1.file.model.FileModelRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@AllArgsConstructor
class FileContractFacade {
    FileFacade facade;

    ResponseEntity sendFile(MultipartFile file, String userId) {
        facade.sendFile(file, userId);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    ResponseEntity delete(String idUser, String fileName) {
        facade.delete(idUser, fileName);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    Page<FileMapper> pagedList(FileModelRequest request) {
        return facade.pagedList(request);
    }

    Page<FileMapper> listsSharedWithMe(FileModelRequest request) {
        return facade.listsSharedWithMe(request);
    }

    void download(String id, String file) {
        facade.download(id, file);
    }

    void downloadSharedWithMe(String id, String file) {
        facade.downloadSharedWithMe(id, file);
    }
}
