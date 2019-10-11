package com.github.dchristofolli.dropbox.file;

import com.github.dchristofolli.dropbox.file.model.FileModel;
import com.github.dchristofolli.dropbox.file.service.FileService;
import com.github.dchristofolli.dropbox.user.mapper.UserMapperImpl;
import com.github.dchristofolli.dropbox.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@AllArgsConstructor
public class FileFacade {
    FileService fileService;
    UserService userService;

    public void sendFile(MultipartFile file, String userId) {
        fileService.send(file, userService.findById(userId));
    }

    public void delete(String idUser, String fileName) throws IOException {
        fileService.delete(userService.findById(idUser), fileName);
    }

    public Page<FileModel> pagedList(int page, int quantity, String user) {
        return fileService.pagedList(page, quantity, userService.findById(user));
    }

    public Page<FileModel> listsSharedWithMe(int page, int quantity, String user) {
        return fileService.listsSharedWithMe(page, quantity, user);
    }

    public void download(String id, String file) throws IOException {
        fileService.download(id, file);
    }

    public void downloadSharedWithMe(String id, String file) throws IOException {
        download(userService.findById(id).getFollower(), file);
    }
}
