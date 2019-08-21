package com.github.dchristofolli.dropbox.services;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class FTPConnect {
    private final Logger logger = LoggerFactory.getLogger(FTPConnect.class);

    private String host = "ftp";
    private String user = "";
    private String password = "";
    private FTPClient client;

    public FTPClient createConnection() {
        client = new FTPClient();
        try {
            client.connect(host);

            client.login(user, password);

        } catch (IOException e) {

            logger.error(e.getMessage());
        }
        return this.client;
    }

    public void closeConnection() {
        try {
            client.disconnect();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }
}
