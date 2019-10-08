package com.github.dchristofolli.impl.dropbox.controllers;

import com.github.dchristofolli.dropbox.models.UserInput;

import java.util.Arrays;
import java.util.List;

public class CreateUsers {

    public UserInput criaUsuario(){
        UserInput userInput = new UserInput("1234564789", "Teste", "06503908067",
                "teste@teste.com", null, "123456");
        return userInput;
    }

    public List<UserInput> criaListaDeUsuarios(){
        List<UserInput> users = Arrays.asList (new UserInput("1234564789", "Teste",
                "06503908067", "teste@teste.com", null, "123456"),
                new UserInput("74389435", "Testando", "25886505032", "testando@123.com.br",
                        null,"eu2019"),
                new UserInput("78628454", "Testado", "97467980060", "testado@oracle.com",
                        null, "jds7589jh"));
        return users;
    }
}
