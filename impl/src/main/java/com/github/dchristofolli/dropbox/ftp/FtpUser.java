package com.github.dchristofolli.dropbox.ftp;

import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FtpUser {
    private static String nomeUsuario;
    private static boolean novoUsuario;
    private static String senhaUsuario;


    public static UserManager mantemProperties() {
        PropertiesUserManagerFactory umf = new PropertiesUserManagerFactory();
        try {
            new File("users.properties").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        umf.setFile(new File("users.properties"));
        return umf.createUserManager();
    }


    public static void salvaUsuario(String nome, String senha) {
        UserManager userManager = mantemProperties();

        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());

        BaseUser user = new BaseUser();
        user.setAuthorities(authorities);
        user.setName(nome);
        user.setPassword(senha);
        user.setHomeDirectory(getPathUsuario(nome));

        try {
            Runtime.getRuntime().exec("mkdir " + user.getHomeDirectory());
            userManager.save(user);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private static String getPathUsuario(String nome) {
        return System.getProperty("user.dir") + "/servidorFTP/" + nome;
    }

    public static void criarNovoUsuario(FtpRequest request, String command) {
        if (command.contains("USER")) {
            nomeUsuario = request.getArgument();
            novoUsuario = FtpUser.verificaUsuario(nomeUsuario);
        }
        if (command.contains("PASS") && novoUsuario) {
            senhaUsuario = request.getArgument();
            salvaUsuario(nomeUsuario, senhaUsuario);
            novoUsuario = false;
        }
    }

    public static boolean verificaUsuario(String nome) {
        User usuario = null;
        try {
            usuario = mantemProperties().getUserByName(nome);
        } catch (FtpException e) {
            e.getMessage();
        }
        return usuario == null;
    }

}