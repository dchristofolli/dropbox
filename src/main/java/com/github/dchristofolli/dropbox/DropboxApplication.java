package com.github.dchristofolli.dropbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DropboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(DropboxApplication.class, args);
    }

}
