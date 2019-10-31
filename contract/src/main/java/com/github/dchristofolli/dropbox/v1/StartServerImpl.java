package com.github.dchristofolli.dropbox.v1;

import com.github.dchristofolli.dropbox.v1.ftp.StartServer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StartServerImpl {
    public static void startServer() {
        StartServer.run();
    }
}
