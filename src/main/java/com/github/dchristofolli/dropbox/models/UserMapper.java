package com.github.dchristofolli.dropbox.models;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class UserMapper {
    public UserResponse userRequestMapper (UserInput userInput){
        return UserResponse.builder().
                id(userInput.getId()).
                name(userInput.getName()).
                build();
    }


    public UserInput userInputMapper (UserRequest user){
        return UserInput.builder()
                .name(user.getName())
                .email(user.getEmail())
                .cpf(user.getCpf())
                .password(user.getPassword())
                .build();
    }

    public UserResponse userResponseMapper (UserInput user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public List<UserResponse> userResponseList (List<UserInput> userInputList){
        List<UserResponse> list = new ArrayList<>();
        userInputList.forEach(userInputs -> list.add(userResponseMapper(userInputs)));
        return list;
    }
}
