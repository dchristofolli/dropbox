package com.github.dchristofolli.dropbox;

import com.github.dchristofolli.dropbox.ftp.StartServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DropboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(DropboxApplication.class, args);
        StartServer.run();
    }

}
