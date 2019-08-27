package com.github.dchristofolli.dropbox.controllers;

import org.apache.commons.net.ftp.FTPFile;

public class File {
    private String nomeArquivo;
    private String tamanhoArquivo;
    private String data;

    public File(FTPFile arquivoFTP) {
        this.setNomeArquivo(arquivoFTP.getName());
        this.setTamanhoArquivo(arquivoFTP.getSize() + " kb");
        this.setData(arquivoFTP.getRawListing());
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
}
