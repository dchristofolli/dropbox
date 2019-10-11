package com.github.dchristofolli.dropbox.stub;

import com.github.dchristofolli.dropbox.model.UserInput;

import java.util.Collections;
import java.util.List;

public class UserInputStub {

    public static UserInput getUserInput() {
        return UserInput.builder()
                .build();
    }

    public static List<UserInput> getUserInputList() {
        return Collections.singletonList(getUserInput());
    }
}
