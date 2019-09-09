package com.github.dchristofolli.dropbox.server;

import com.github.dchristofolli.dropbox.ftp.FtpUser;
import com.github.dchristofolli.dropbox.ftp.FtpLogin;
import org.apache.ftpserver.ConnectionConfigFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.listener.ListenerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServerConfig {


    private FtpServer server;

    public void start(){
        FtpServerFactory serverFactory = new FtpServerFactory();

        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(2021);
        listenerFactory.setIdleTimeout(120000000);

        ConnectionConfigFactory connectionConfigFactory = new ConnectionConfigFactory();
        connectionConfigFactory.setAnonymousLoginEnabled(false);

        Map<String, Ftplet> map = new HashMap<>();
        map.put("FtpletListeners", new FtpLogin());

        serverFactory.addListener("default", listenerFactory.createListener());
        serverFactory.setFtplets(map);
        serverFactory.setConnectionConfig(connectionConfigFactory.createConnectionConfig());
        serverFactory.setUserManager(FtpUser.criaUserManager());
        server = serverFactory.createServer();

        try {
            server.start();
        } catch (FtpException e) {
            e.getMessage();
        }
    }

    public void stop() {
        if (!server.isStopped()) {
            server.stop();
            server = null;
        }
    }
}