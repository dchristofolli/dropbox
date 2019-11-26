package com.github.dchristofolli.dropbox.v1.ftp;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StartServer {
    private static FtpService ftpService = new FtpService();

    public static void run() {
        ftpService.start();
    }

    public static void restart() {
        ftpService.stop();
        ftpService.start();
    }
}
