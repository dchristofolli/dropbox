package com.github.dchristofolli.dropbox;

<<<<<<< HEAD

=======
import com.github.dchristofolli.dropbox.ftp.StartServer;
>>>>>>> parent of 6b10073... criado handleEception para http status 500
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DropboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(DropboxApplication.class, args);
<<<<<<< HEAD
//        FtpStart.run();
=======
        StartServer.run();
>>>>>>> parent of 6b10073... criado handleEception para http status 500
    }
}
