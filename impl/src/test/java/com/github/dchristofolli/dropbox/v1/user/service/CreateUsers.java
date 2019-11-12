package com.github.dchristofolli.dropbox.v1.user.service;

import com.github.dchristofolli.dropbox.v1.user.model.UserModel;

import java.util.Arrays;
import java.util.List;

class CreateUsers {
    static UserModel createUser() {
        return new UserModel("1234564789", "Test", "06503908067",
                "test@test.com", null, "123456");
    }
    static List<UserModel> createUsersList() {
        return Arrays.asList(new UserModel("12345", "Mock", "38002710010", "mock@mockito.com",
                        null,"mock123"),
                new UserModel("74389435", "Testing", "58129888033", "testing@test.com",
                        null, "test123"),
                new UserModel("78628454", "Tested", "58129888033", "tested@junit.com",
                        null, "junit5"));
    }
}
