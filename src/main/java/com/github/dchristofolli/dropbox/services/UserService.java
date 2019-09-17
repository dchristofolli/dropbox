package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.ftp.FtpUser;
import com.github.dchristofolli.dropbox.models.UserInput;
import com.github.dchristofolli.dropbox.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserInput> listarUsers() {
        if (userRepository != null) {
            return this.userRepository.findAll();
        }
        return null;
    }

    public Optional<UserInput> listarPorId(String id) {
        String idAux = id;
        if (idAux != null) {
            return this.userRepository.findById(idAux);
        }
        return null;
    }

    public UserInput cadastrar(UserInput userInput) {
        FtpUser.salvaUsuario(userInput.getNome(), userInput.getSenha());
        userRepository.save(userInput);
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
            user.setSeguidor(idVisitante);
            return atualizar(user);
        }
        return null;
    }
}
