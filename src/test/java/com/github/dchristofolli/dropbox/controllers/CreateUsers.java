package com.github.dchristofolli.dropbox.controllers;

import com.github.dchristofolli.dropbox.models.UserInput;
import com.github.dchristofolli.dropbox.models.UserResponse;

import java.util.Arrays;
import java.util.List;

public class CreateUsers {

    UserInput createUser() {
        UserInput userInput = new UserInput("1234564789", "Test", "06503908067",
                "test@test.com", null, "123456");
        return userInput;
    }

    List<UserResponse> createUsersList() {
        List<UserResponse> users = Arrays.asList(new UserResponse("1234564789", "Test"),
                new UserResponse("74389435", "Testing"),
                new UserResponse("78628454", "Tested"));
        return users;
    }
}
