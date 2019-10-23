package com.github.dchristofolli.dropbox.v1.file.service;

import com.github.dchristofolli.dropbox.v1.file.model.FileMapper;
import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import com.github.dchristofolli.dropbox.v1.user.service.UserService;
import com.github.dchristofolli.dropbox.v1.file.model.FileModel;
import com.github.dchristofolli.dropbox.v1.file.model.FileModelList;
import com.github.dchristofolli.dropbox.v1.ftp.FtpConnect;
import lombok.AllArgsConstructor;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class FileService {
    private UserService userService;
    private FileMapper fileMapper;

    public void send(MultipartFile file, UserModel user) {
        FTPClient connect = FtpConnect.connect(user.getName(), user.getPassword());
        try {
            connect.storeFile(file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void delete(UserModel user, String fileName) {
        FTPClient connect = FtpConnect.connect(user.getName(), user.getPassword());
        try {
            connect.deleteFile(fileName);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void download(String id, String fileName) {
        List<UserModel> users = userService.findAll();
        UserModel user = userService.findById(id);
        if (users.contains(user)) {
            FTPClient ftpClient = FtpConnect.connect(userService
                    .findById(id)
                    .getName(), userService
                    .findById(id)
                    .getPassword());
            try {
                ftpClient.retrieveFile(fileName,
                        new FileOutputStream("/home/daniel/DownloadsFTP/" + fileName));
            } catch (IOException e) {
                e.getMessage();
            }

        }
    }

    private FileModelList showUserFiles(UserModel user) {
        FTPClient connect = FtpConnect.connect(user.getName(), user.getPassword());
        try{
            FTPFile[] files = connect.listFiles();
            ArrayList<FileModel> fileInputs = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Page<FileModel> pagedList(int page, int quantity, UserModel user) { //TODO revisar a lista paginada
        return null;
    }

    public Page<FileModel> listsSharedWithMe(int page, int quantity, String user) {
        return pagedList(page, quantity, userService.findById(userService.findById(user).getFollower()));
    }
}
