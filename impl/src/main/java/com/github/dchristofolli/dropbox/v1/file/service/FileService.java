package com.github.dchristofolli.dropbox.v1.file.service;

import com.github.dchristofolli.dropbox.v1.file.model.FileModel;
import com.github.dchristofolli.dropbox.v1.file.model.FileModelList;
import com.github.dchristofolli.dropbox.v1.ftp.FtpConnect;
import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import com.github.dchristofolli.dropbox.v1.user.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class FileService {
    private UserService userService;

    public void send(MultipartFile file, UserModel user) {
        FTPClient connect = FtpConnect.connect(user.getName(), user.getPassword());
        try {
            connect.storeFile(file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void delete(UserModel user, String fileName) throws IOException {
        FTPClient connect = FtpConnect.connect(user.getName(), user.getPassword());
        connect.deleteFile(fileName);
    }

    public void download(String id, String fileName) throws IOException { //TODO tratar as exceções com try catch
        List<UserModel> users = userService.findAll();
        UserModel user = userService.findById(id);
        if (users.contains(user)) {
            FTPClient ftpClient = FtpConnect.connect(userService
                    .findById(id)
                    .getName(), userService
                    .findById(id)
                    .getPassword());
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream("/home/dchristofolli/DownloadsFTP/" + fileName);
            ftpClient.retrieveFile(fileName, fileOutputStream);
        }
    }

    private FileModelList showUserFiles(UserModel user) {
        FTPClient connect = FtpConnect.connect(user.getName(), user.getPassword());
        try {
            FTPFile[] files = connect.listFiles();
            FileModelList fileModelList = null;
            for (FTPFile ftpFile : files) {
                fileModelList.add(new FileModel(ftpFile));
            }
            return fileModelList;
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

    public Page<FileModel> pagedList(int page, int quantity, UserModel user) { //TODO criar objeto request com os 3 params
        return FtpConnect.pagedList((List<FileModel>) Objects.requireNonNull(showUserFiles(user)));
    }

    public Page<FileModel> listsSharedWithMe(int page, int quantity, String user) {
        return pagedList(page, quantity, userService.findById(userService.findById(user).getFollower()));
    }
}
