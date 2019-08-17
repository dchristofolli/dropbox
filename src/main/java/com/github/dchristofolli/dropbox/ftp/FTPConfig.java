package com.github.dchristofolli.dropbox.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


import java.io.IOException;

public class FTPConfig {
    public FTPConfig() throws IOException {
        var client = new FTPClient();

        try {
            client.connect("localhost");
        } catch (IOException ex) {
            throw new IOException("Não foi possível conectar-se ao FTP.");
        }

        if (FTPReply.isPositiveCompletion(client.getReplyCode())) {
            try {
                client.login("admin", "1234");
                client.setFileType(FTPClient.BINARY_FILE_TYPE);
            } catch (IOException ex) {
                throw new IOException("Não foi possível logar-se ao FTP.");
            }
        } else {
            throw new IOException("Não foi possível conectar-se ao FTP.");
        }
    }
}
