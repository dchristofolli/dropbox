package com.github.dchristofolli;


import com.github.dchristofolli.impl.ftp.FtpStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DropboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(DropboxApplication.class, args);
        FtpStart.run();
    }

}
