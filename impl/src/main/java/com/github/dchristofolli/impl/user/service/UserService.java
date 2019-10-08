package com.github.dchristofolli.user.service;

import com.github.dchristofolli.contract.v1.user.model.response.UserResponse;
import com.github.dchristofolli.ftp.FtpUser;
import com.github.dchristofolli.user.exception.ApiException;
import com.github.dchristofolli.user.mapper.UserMapper;
import com.github.dchristofolli.user.model.UserInput;
import com.github.dchristofolli.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public List<UserResponse> showAllUsers() { // TODO retornar objetos lista em vez de List
        List<UserResponse> users = userRepository.findAll().stream().map(this::mapToInput).collect(Collectors.toList());
        if (users.isEmpty()) throw new ApiException("Nenhum usuário encontrado", HttpStatus.NOT_FOUND);
        return users;
    }

    public UserResponse showUserById(String id) {
        return mapToInput(userRepository.findById(id)
                .orElseThrow(() -> new ApiException("Usuário não encontrado", HttpStatus.NOT_FOUND)));
    }

    public UserResponse saveUser(UserInput user) {
        FtpUser.saveUser(user.getName().toLowerCase(), user.getPassword());
        return userMapper.userInputToUserResponse(userRepository.save(user));
    }

    public UserInput updateUser(UserInput userInput) {
        return this.userRepository.save(userInput);
    }

    public void deleteUser(UserInput userInput) {
        this.userRepository.deleteById(userInput.getId());
    }

    public UserInput addFollower(String userId, String followerId) {
        return userRepository.findById(userId)
                .map(userInput -> {
                    userInput.setFollower(followerId);
                    return updateUser(userInput);
                }).orElseThrow(() -> new ApiException("Não encontrado", HttpStatus.NOT_FOUND));
    }

    public UserResponse mapToInput(UserInput userInput) {
        return userMapper.userResponseToUserInput(userInput);
    }

    public Optional<UserInput> getUserById(String id) {
        return userRepository.findById(id);
    }
}

