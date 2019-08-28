package com.github.dchristofolli.dropbox.ftp;

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
public class FTPConnect {
    private FtpServer server;

    public boolean start(){
        FtpServerFactory serverFactory = new FtpServerFactory();

        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(2021);
        listenerFactory.setIdleTimeout(120000000);

        ConnectionConfigFactory connectionConfigFactory = new ConnectionConfigFactory();
        connectionConfigFactory.setAnonymousLoginEnabled(false);

        Map<String, Ftplet> map = new HashMap<>();
        map.put("FTPListeners", new FtpUserLogin());

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
        return true;
    }

    public void stop() {
        if (!server.isStopped()) {
            server.stop();
            server = null;
        }
    }

    public void restart() throws FtpException {
        server.stop();
        server.start();
    }


}
