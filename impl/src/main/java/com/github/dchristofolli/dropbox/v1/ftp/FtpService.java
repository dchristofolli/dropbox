package com.github.dchristofolli.dropbox.v1.ftp;

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
public class FtpService {


    private FtpServer server;

    void start() {
        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory listenerFactory = getListenerFactory();
        ConnectionConfigFactory connectionConfigFactory = getConnectionConfigFactory();
        serverConfig(serverFactory, listenerFactory, connectionConfigFactory);

        try {
            server.start();
        } catch (FtpException e) {
            e.getMessage();
        }
    }

    private void serverConfig(FtpServerFactory serverFactory, ListenerFactory listenerFactory, ConnectionConfigFactory connectionConfigFactory) {
        Map<String, Ftplet> map = new HashMap<>();
        map.put("FtpletListeners", new FtpLogin());
        factoryConfig(serverFactory, listenerFactory, connectionConfigFactory, map);
    }

    private void factoryConfig(FtpServerFactory serverFactory, ListenerFactory listenerFactory, ConnectionConfigFactory connectionConfigFactory, Map<String, Ftplet> map) {
        serverFactory.addListener("default", listenerFactory.createListener());
        serverFactory.setFtplets(map);
        serverFactory.setConnectionConfig(connectionConfigFactory.createConnectionConfig());
        serverFactory.setUserManager(FtpUser.propertiesManager());
        server = serverFactory.createServer();
    }

    private ConnectionConfigFactory getConnectionConfigFactory() {
        ConnectionConfigFactory connectionConfigFactory = new ConnectionConfigFactory();
        connectionConfigFactory.setAnonymousLoginEnabled(false);
        return connectionConfigFactory;
    }

    private ListenerFactory getListenerFactory() {
        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(2021);
        listenerFactory.setIdleTimeout(120000000);
        return listenerFactory;
    }

    void stop() {
        if (!server.isStopped()) {
            server.stop();
            server = null;
        }
    }
}
