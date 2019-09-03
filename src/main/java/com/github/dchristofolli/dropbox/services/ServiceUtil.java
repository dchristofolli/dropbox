package com.github.dchristofolli.dropbox.services;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ServiceUtil {

    public static FTPClient conexao(String user, String pass){
        FTPClient ftpClient = new FTPClient();
        try{
            ftpClient.connect("127.0.0.1", 2021);
            ftpClient.login(user, pass);
        } catch (IOException e){
            e.getMessage();
        }
        return ftpClient;
    }
}
