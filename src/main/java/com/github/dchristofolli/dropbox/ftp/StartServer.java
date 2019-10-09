package com.github.dchristofolli.dropbox.ftp;

import com.github.dchristofolli.dropbox.services.FtpService;

public class StartServer {
    private static FtpService ftpService = new FtpService();
    public StartServer(){

    }

    public static void run(){
        ftpService.start();
    }
    public static void restart(){
        ftpService.stop();
        ftpService.start();
    }
}
