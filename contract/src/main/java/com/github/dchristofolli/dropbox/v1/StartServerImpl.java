package com.github.dchristofolli.dropbox.v1;

import com.github.dchristofolli.dropbox.v1.ftp.StartServer;

public class StartServerImpl {
    static StartServer startServer;
    public static void startServer(){
        startServer.run();
    }
}
