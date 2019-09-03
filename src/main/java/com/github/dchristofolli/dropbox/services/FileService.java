package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.controllers.FileInput;
import com.github.dchristofolli.dropbox.controllers.UserInput;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;

@Service
public class FileService {
    @Autowired
    UserService userService;

    public boolean enviar(MultipartFile arquivo, UserInput userInput) {
        FTPClient conexao = ServiceUtil.conexao(userInput.getNome(), userInput.getSenha());
        try{
            return conexao.storeFile(arquivo.getOriginalFilename(), arquivo.getInputStream());
        } catch (IOException e){
            e.getMessage();
            return false;
        }
    }

    public Boolean deletar(String arquivo,UserInput user) throws IOException {
        FTPClient conexao = ServiceUtil.conexao(user.getNome(), user.getSenha());
        return conexao.deleteFile(arquivo);
    }

    public ArrayList<FileInput> listar(UserInput user) throws IOException {
        FTPClient conexao = ServiceUtil.conexao(user.getNome(), user.getSenha());
        try{
            FTPFile[] files = conexao.listFiles();
            ArrayList<FileInput> fileInputs = new ArrayList<>();
            for(FTPFile ftpFile : files){
                FileInput arquivo = new FileInput(ftpFile);
                fileInputs.add(arquivo);
        }
            return fileInputs;
        } catch (IOException e){
            e.getMessage();
            return null;
        }

    }

//    public void compartilhar(String user){
//        FTPClient conexao = ServiceUtil.conexao(user);
//
//    }



}
