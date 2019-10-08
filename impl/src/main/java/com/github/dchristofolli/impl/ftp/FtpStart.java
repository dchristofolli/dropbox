package com.github.dchristofolli.ftp;

public class FtpStart {
    private static FtpService ftpService = new FtpService();
    public FtpStart(){

    }

    public static void run(){
        ftpService.start();
    }
    public static void restart(){
        ftpService.stop();
        ftpService.start();
    }
}
