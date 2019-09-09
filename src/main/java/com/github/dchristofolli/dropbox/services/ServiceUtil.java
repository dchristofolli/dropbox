package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.controllers.FileInput;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceUtil {

    static FTPClient conexao(String user, String pass) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("127.0.0.1", 2021);
            ftpClient.login(user, pass);
        } catch (IOException e) {
            e.getMessage();
        }
        return ftpClient;
    }

//    public static Page<FileInput> pagina(ArrayList<FileInput> arquivos, int pagina, int quantidade) {
//
//        List<FileInput> listaArquivos = new ArrayList<>();
//
//        for (FileInput arquivo : arquivos) {
//            listaArquivos.add(arquivo);
//        }
//        int maximoArquivos = ((quantidade * pagina) > listaArquivos.size()) ?
//                                                        listaArquivos.size() : quantidade * (pagina);
//
//        Page<FileInput> paginaFiles = new PageImpl<>(listaArquivos.subList((pagina - 1) *
//                quantidade, maximoArquivos), PageRequest.of(pagina, quantidade), maximoArquivos);
//
//        return paginaFiles;
//    }
}
