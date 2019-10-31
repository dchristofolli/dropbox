package com.github.dchristofolli.dropbox.v1.ftp;

import org.apache.ftpserver.ftplet.*;

public class FtpLogin implements Ftplet {

    @Override
    public void init(FtpletContext ftpletContext) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public FtpletResult beforeCommand(FtpSession session, FtpRequest request) {
        String cmd = request.getCommand();
        if (cmd.contains("USER") && cmd.contains("PASS")) {
            FtpUser.saveNewUser(request, cmd);
            return FtpletResult.DEFAULT;
        }
        return FtpletResult.DEFAULT;
    }

    @Override
    public FtpletResult afterCommand(FtpSession session, FtpRequest request, FtpReply reply) {
        return null;
    }

    @Override
    public FtpletResult onConnect(FtpSession session) {
        return null;
    }

    @Override
    public FtpletResult onDisconnect(FtpSession session) {
        return null;
    }
}
