package com.github.dchristofolli.dropbox.v1.ftp;


import com.github.dchristofolli.dropbox.v1.file.model.FileMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static Page<FileMapper> pagedList(List<FileMapper> files) {
        List<FileMapper> list = new ArrayList<>(files);
        return new PageImpl<>(list);
    }
}
