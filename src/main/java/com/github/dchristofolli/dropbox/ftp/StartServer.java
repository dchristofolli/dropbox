package com.github.dchristofolli.dropbox.ftp;

import com.github.dchristofolli.dropbox.server.ServerConfig;

public class StartServer {
    private static ServerConfig serverConfig = new ServerConfig();
    public StartServer(){

    }

    public static void run(){
        serverConfig.start();
    }
    public void restart(){
        serverConfig.stop();
        serverConfig.start();
    }
}
