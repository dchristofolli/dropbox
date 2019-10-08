package com.github.dchristofolli.ftp;


import com.github.dchristofolli.file.model.FileInput;
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
            ftpClient.login(user, pass);
        } catch (IOException e) {
            e.getMessage();
        }
        return ftpClient;
    }

    public static Page<FileInput> page(ArrayList<FileInput> files) {
        List<FileInput> fileList = new ArrayList<>();
        fileList.addAll(files);
        return new PageImpl<>(fileList);
    }
}
