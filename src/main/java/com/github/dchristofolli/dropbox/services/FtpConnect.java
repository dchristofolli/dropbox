package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.models.FileInput;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FtpConnect {
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

    public static Page<FileInput> paginacao(ArrayList<FileInput> files,
                                            int pagina, int qtPorPagina) {
        List<FileInput> lista = new ArrayList<>();
        for (FileInput f : files) {
            lista.add(f);
        }
        Page<FileInput> filePages = new PageImpl<>(lista);
        return filePages;
    }
}
