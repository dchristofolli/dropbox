package com.github.dchristofolli.impl.contract.v1.user.mapper;

import com.github.dchristofolli.impl.contract.v1.user.model.request.UserRequest;
import com.github.dchristofolli.impl.contract.v1.user.model.response.UserResponse;
import com.github.dchristofolli.impl.user.model.UserInput;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class UserMapper {
    public UserResponse userResponseToUserInput(UserInput userInput) {
        return UserResponse.builder().
                id(userInput.getId()).
                name(userInput.getName()).
                build();
    }


    public UserInput userRequestToUserInput(UserRequest user) {
        return UserInput.builder()
                .name(user.getName())
                .email(user.getEmail())
                .cpf(user.getCpf())
                .password(user.getPassword())
                .build();
    }

    public UserResponse userInputToUserResponse(UserInput user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public List<UserResponse> userResponseList (List<UserInput> userInputList){
        List<UserResponse> list = new ArrayList<>();
        userInputList.forEach(userInputs -> list.add(userInputToUserResponse(userInputs)));
        return list;
    }

    public UserInput userInputToUserResponse(UserResponse response) {
        return UserInput.builder()
                .id(response.getId())
                .name(response.getName())
                .build();
    }
}
