package com.github.dchristofolli.dropbox.v1.file.model;

import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;

public class FileModel {

    @Autowired
    UserModel userModel;

    public FileModel(FTPFile arquivoFTP) {
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
