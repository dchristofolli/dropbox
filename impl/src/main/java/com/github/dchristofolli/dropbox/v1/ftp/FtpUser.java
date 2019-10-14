package com.github.dchristofolli.dropbox.v1.ftp;

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
    private static boolean isNewUser;


    static UserManager propertiesManager() {
        PropertiesUserManagerFactory umf = new PropertiesUserManagerFactory();
        try {
            new File("users.properties").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        umf.setFile(new File("users.properties"));
        return umf.createUserManager();
    }


    public static void saveUser(String userName, String pass) {
        UserManager userManager = propertiesManager();

        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());

        BaseUser user = new BaseUser();
        user.setAuthorities(authorities);
        user.setName(userName);
        user.setPassword(pass);
        user.setHomeDirectory(getUserPath(userName));

        try {
            Runtime.getRuntime().exec("mkdir " + user.getHomeDirectory());
            userManager.save(user);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private static String getUserPath(String userName) {
        return System.getProperty("user.dir") + "/ftpFiles/" + userName;
    }

    static void saveUser(FtpRequest request, String command) {
        if (command.contains("USER")) {
            userName = request.getArgument();
            isNewUser = FtpUser.checkUser(userName);
        }
        if (command.contains("PASS") && isNewUser) {
            String userPassword = request.getArgument();
            saveUser(userName, userPassword);
            isNewUser = false;
        }
    }

    private static boolean checkUser(String nome) {
        User user = null;
        try {
            user = propertiesManager().getUserByName(nome);
        } catch (FtpException e) {
            e.getMessage();
        }
        return user == null;
    }

}
