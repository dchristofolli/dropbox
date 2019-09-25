package com.github.dchristofolli.dropbox.models;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;

public class FileInput {

    @Autowired
    UserInput userInput;

    public FileInput(FTPFile arquivoFTP) {
        this.setNomeArquivo(arquivoFTP.getName());
        this.setTamanhoArquivo(arquivoFTP.getSize() + " kb");
        this.setData(arquivoFTP.getRawListing());
    }

    private void setNomeArquivo(String nomeArquivo) {
    }

    private void setTamanhoArquivo(String tamanhoArquivo) {
    }

    private void setData(String data) {
    }

}
