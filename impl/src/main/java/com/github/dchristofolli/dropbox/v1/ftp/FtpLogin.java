package com.github.dchristofolli.dropbox.v1.ftp;

import org.apache.ftpserver.ftplet.*;

import java.io.IOException;

public class FtpLogin implements Ftplet {

    @Override
    public void init(FtpletContext ftpletContext) throws FtpException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public FtpletResult beforeCommand(FtpSession session, FtpRequest request) throws FtpException, IOException {
        String cmd = request.getCommand();
        if(cmd.contains("USER") && cmd.contains("PASS")){
            FtpUser.saveUser(request, cmd);
            return FtpletResult.DEFAULT;
        }
        return FtpletResult.DEFAULT;
    }

    @Override
    public FtpletResult afterCommand(FtpSession session, FtpRequest request, FtpReply reply) throws FtpException, IOException {
        return null;
    }

    @Override
    public FtpletResult onConnect(FtpSession session) throws FtpException, IOException {
        return null;
    }

    @Override
    public FtpletResult onDisconnect(FtpSession session) throws FtpException, IOException {
        return null;
    }
}
