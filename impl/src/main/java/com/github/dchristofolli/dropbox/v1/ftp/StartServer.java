package com.github.dchristofolli.dropbox.v1.ftp;


public class StartServer {
    private static FtpService ftpService = new FtpService();

    public StartServer() {

    }

    public static void run() {
        ftpService.start();
    }

    public static void restart() {
        ftpService.stop();
        ftpService.start();
    }
}
