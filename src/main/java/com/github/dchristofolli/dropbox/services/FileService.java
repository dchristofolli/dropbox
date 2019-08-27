package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.controllers.File;
import com.github.dchristofolli.dropbox.controllers.User;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class FileService {
    @Autowired
    UserService userService;
    @Autowired
    ServiceUtil serviceUtil;

    public boolean enviaArquivo(MultipartFile arquivo, Optional<User> user){
        FTPClient ftpClient = ServiceUtil.conexao(String.valueOf(user.get()));
        try {
            return ftpClient.storeFile(arquivo.getOriginalFilename(), arquivo.getInputStream());
        } catch (IOException e){
            e.getMessage();
            return false;
        }
    }

    public boolean deletaArquivo(String nomeArquivo, User user){
        FTPClient ftpClient = ServiceUtil.conexao(user.getNome());
        try {
            return ftpClient.deleteFile(nomeArquivo);
        } catch (IOException e){
            e.getMessage();
            return false;
        }
    }

    public ArrayList<File> listaArquivos(String user) {
        FTPClient ftpClient = ServiceUtil.conexao(user);
        try{
            FTPFile[] ftpFiles = ftpClient.listFiles();
            ArrayList<File> files = new ArrayList<>();
            for (FTPFile ftpFile: ftpFiles){
                File file = new File(ftpFile);
                files.add(file);
            }
            return files;
        }
        catch (IOException e){
            e.getMessage();
            return null;
        }
    }
    public void download(User user, String nomeArquivo){
        FTPClient ftpClient = ServiceUtil.conexao(user.getNome());
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream("./dropbox/Downloads/" + nomeArquivo);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try{
            ftpClient.retrieveFile(nomeArquivo, fileOutputStream);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
