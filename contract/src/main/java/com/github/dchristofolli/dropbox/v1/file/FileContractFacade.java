package com.github.dchristofolli.dropbox.v1.file;

import com.github.dchristofolli.dropbox.v1.file.model.FileModel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@AllArgsConstructor
class FileContractFacade {
    FileFacade facade;

    void sendFile(MultipartFile file, String userId) {
        //
        facade.sendFile(file, userId);

    }

    ResponseEntity delete(String idUser, String fileName) throws IOException {
        facade.delete(idUser, fileName);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    Page<FileModel> pagedList(int page, int quantity, String user) {
        return facade.pagedList(page, quantity, user);
    }

    Page<FileModel> listsSharedWithMe(int page, int quantity, String user) {
        return facade.listsSharedWithMe(page, quantity, user);
    }

    void download(String id, String file) throws IOException {
        facade.download(id, file);
    }

    public void downloadSharedWithMe(String id, String file) throws IOException {
        facade.downloadSharedWithMe(id, file);
    }
}
