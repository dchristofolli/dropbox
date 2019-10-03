package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.exceptions.ApiException;
import com.github.dchristofolli.dropbox.ftp.FtpUser;
import com.github.dchristofolli.dropbox.models.UserInput;
import com.github.dchristofolli.dropbox.models.UserMapper;
import com.github.dchristofolli.dropbox.models.UserResponse;
import com.github.dchristofolli.dropbox.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public List<UserResponse> showAllUsers() { // TODO retornar objetos lista em vez de List
        List<UserResponse> users = userRepository.findAll().stream().map(this::mapToModel).collect(Collectors.toList());
        if (users.isEmpty()) throw new ApiException("Nenhum usuário encontrado", HttpStatus.NOT_FOUND);
        return users;
    }

    public UserResponse showUserById(String id) {
        return mapToModel(userRepository.findById(id)
                .orElseThrow(() -> new ApiException("Usuário não encontrado", HttpStatus.NOT_FOUND)));
    }

    public UserResponse saveUser(UserInput user) {
        FtpUser.salvaUsuario(user.getName().toLowerCase(), user.getPassword());
        return userMapper.userResponseMapper(userRepository.save(user));
    }

    public UserInput updateUser(UserInput userInput) {
        return this.userRepository.save(userInput);
    }

    public void deleteUser(UserInput userInput) {
        this.userRepository.deleteById(userInput.getId());
    }

    public UserInput allowFollower(String userId, String followerId) {
        return userRepository.findById(userId)
                .map(userInput -> {
                    userInput.setFollower(followerId);
                    return updateUser(userInput);
                }).orElseThrow(() -> new ApiException("Não encontrado", HttpStatus.NOT_FOUND));
    }

    public UserResponse mapToModel(UserInput userInput) {
        return userMapper.userRequestMapper(userInput);
    }

}
