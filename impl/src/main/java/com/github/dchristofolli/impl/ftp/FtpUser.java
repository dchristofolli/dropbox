package com.github.dchristofolli.ftp;

import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FtpUser {
    private static String userName;
    private static boolean newUser;
    private static String userPass;


    public static UserManager propertiesManager() {
        PropertiesUserManagerFactory umf = new PropertiesUserManagerFactory();
        try {
            new File("users.properties").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        umf.setFile(new File("users.properties"));
        return umf.createUserManager();
    }

    public static void saveUser(String nome, String senha) {
        UserManager userManager = propertiesManager();

        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());

        BaseUser user = new BaseUser();
        user.setAuthorities(authorities);
        user.setName(nome);
        user.setPassword(senha);
        user.setHomeDirectory(getPathUser(nome));

        try {
            Runtime.getRuntime().exec("mkdir " + user.getHomeDirectory());
            userManager.save(user);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private static String getPathUser(String nome) {
        return System.getProperty("user.dir") + "/servidorFTP/" + nome;
    }

    public static void createNewUser(FtpRequest request, String command) {
        if (command.contains("USER")) {
            userName = request.getArgument();
            newUser = FtpUser.verifyUser(userName);
        }
        if (command.contains("PASS") && newUser) {
            userPass = request.getArgument();
            saveUser(userName, userPass);
            newUser = false;
        }
    }

    public static boolean verifyUser(String nome) {
        User usuario = null;
        try {
            usuario = propertiesManager().getUserByName(nome);
        } catch (FtpException e) {
            e.getMessage();
        }
        return usuario == null;
    }

}
