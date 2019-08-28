package com.github.dchristofolli.dropbox.services;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ServiceUtil {

    public static FTPClient conexao(String user){
        FTPClient ftpClient = new FTPClient();
        try{
            ftpClient.connect("localhost", 21);
            ftpClient.login(user, "");
        } catch (IOException e){
            e.getMessage();
        }
        return ftpClient;
    }
}
