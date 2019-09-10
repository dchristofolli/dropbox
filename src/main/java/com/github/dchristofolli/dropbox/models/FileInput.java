package com.github.dchristofolli.dropbox.models;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;

public class FileInput {
    private String nomeArquivo;
    private String tamanhoArquivo;
    private String data;
//    private String dono;

    @Autowired
    UserInput userInput;
    public FileInput(FTPFile arquivoFTP) {
        this.setNomeArquivo(arquivoFTP.getName());
        this.setTamanhoArquivo(arquivoFTP.getSize() + " kb");
        this.setData(arquivoFTP.getRawListing());
//        this.setDono(userInput.getNome());
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(String tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

//    public String getDono() {
//        return dono;
//    }
//
//    public void setDono(String dono) {
//        this.dono = dono;
//    }
}
