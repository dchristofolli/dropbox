package com.github.dchristofolli.dropbox.v1.file;

import com.github.dchristofolli.dropbox.v1.file.model.FileMapper;
import com.github.dchristofolli.dropbox.v1.file.model.FileModelRequest;
import com.github.dchristofolli.dropbox.v1.file.service.FileService;
import com.github.dchristofolli.dropbox.v1.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@AllArgsConstructor
public class FileFacade {
    private FileService fileService;
    private UserService userService;

    public void sendFile(MultipartFile file, String userId) {
        fileService.send(file, userService.findById(userId));
    }

    public void delete(String idUser, String fileName)  {
        fileService.delete(userService.findById(idUser), fileName);
    }

    public Page<FileMapper> pagedList(FileModelRequest request) {
        return fileService.pagedList(request);
    }

    public Page<FileMapper> listsSharedWithMe(FileModelRequest request) {
        return fileService.listsSharedWithMe(request);
    }

    public void download(String id, String file) {
        fileService.download(id, file);
    }

    public void downloadSharedWithMe(String id, String file) {
        download(userService.findById(id).getFollower(), file);
    }

}
