package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.ftp.FtpUser;
import com.github.dchristofolli.dropbox.models.UserInput;
import com.github.dchristofolli.dropbox.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserInput> listarUsers() {
        if (ObjectUtils.isEmpty(userRepository)) {
            return this.userRepository.findAll();
        }
        return Collections.emptyList();
    }

    public Optional<UserInput> listarPorId(String id) {
        String idAux = id;
        if (idAux != null) {
            return this.userRepository.findById(idAux);
        }
        return Optional.empty();
    }

    public boolean userExistsById(String usuario) {
        List<UserInput> users = listarUsers();
        if (users.contains(usuario)) {
            return true;
        }
        return false;
    }

    public UserInput cadastrar(UserInput userInput) {
        FtpUser.salvaUsuario(userInput.getNome().toLowerCase(), userInput.getSenha());
        userRepository.save(userInput);
        return userInput;
    }

    public UserInput atualizar(UserInput userInput) {
        return this.userRepository.save(userInput);
    }

    @NotNull
    public void remover(String id) {
        this.userRepository.deleteById(id);

    }

    public UserInput permiteVisitante(String idUsuario, String idVisitante) {
        return userRepository.findById(idUsuario)
                .map(userInput -> {
                    userInput.setSeguidor(idVisitante);
                    return atualizar(userInput);
                }).orElse(null);
    }
}
