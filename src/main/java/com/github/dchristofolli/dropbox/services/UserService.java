package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.ftp.FtpUser;
import com.github.dchristofolli.dropbox.models.UserInput;
import com.github.dchristofolli.dropbox.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<List<UserInput>> listarUsers() {
        if (userRepository.count() > 0) {
            return Optional.ofNullable(this.userRepository.findAll());
        }
        return null;
    }

    public Optional<UserInput> listarPorId(String id) {
        return this.userRepository.findById(id);
    }

    public UserInput cadastrar(UserInput myUserInput) {
        FtpUser.salvaUsuario(myUserInput.getNome(), myUserInput.getSenha());
        userRepository.save(myUserInput);
        return new UserInput();
    }

    public UserInput atualizar(UserInput userInput) {
        return this.userRepository.save(userInput);
    }

    @NotNull
    public void remover(String id) {
            this.userRepository.deleteById(id);

    }

    public UserInput permiteVisitante(String idUsuario, String idVisitante) {
        if (userRepository.existsById(idUsuario)) {
            Optional<UserInput> userInput = userRepository.findById(idUsuario);
            UserInput user = userInput.get();
            user.setSeguidores(idVisitante);
            return atualizar(user);
        }
        return null;
    }
}
