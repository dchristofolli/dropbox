package com.github.dchristofolli.contract.v1.file.model;

import com.github.dchristofolli.impl.user.model.UserInput;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;

public class FileInput {

    @Autowired
    UserInput userInput;

    public FileInput(FTPFile arquivoFTP) {
    }

    private void setNomeArquivo(String nomeArquivo) {
    }

    private void setTamanhoArquivo(String tamanhoArquivo) {
    }

    private void setData(String data) {
    }

}
