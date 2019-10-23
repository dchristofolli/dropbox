package com.github.dchristofolli.dropbox.v1.ftp;


import com.github.dchristofolli.dropbox.v1.file.model.FileMapper;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FtpConnect {
    public static FTPClient connect(String user, String pass) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("127.0.0.1", 2021);
            ftpClient.login(user.toLowerCase(), pass);
        } catch (IOException e) {
            e.getMessage();
        }
        return ftpClient;
    }

<<<<<<< HEAD
    public static Page<FileModel> pagedList(List<FileModel> files) {
        List<FileModel> list = new ArrayList<>();
        for (FileModel f : files) {
            list.add(f);
        }
        Page<FileModel> filePages = new PageImpl<>(list);
        return filePages;
=======
    public static Page<FileMapper> pagedList(ArrayList<FileMapper> files,
                                             int page, int quantity) {
        List<FileMapper> list = new ArrayList<>();
        list.addAll(files);
        return new PageImpl<>(list);
>>>>>>> c36e740bfccbbe49aeca563585c738fd3efe0afb
    }
}
