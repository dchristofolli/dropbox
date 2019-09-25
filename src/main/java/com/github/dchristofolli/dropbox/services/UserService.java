package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.exceptions.ApiException;
import com.github.dchristofolli.dropbox.ftp.FtpUser;
import com.github.dchristofolli.dropbox.models.UserInput;
import com.github.dchristofolli.dropbox.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserInput> listarUsers() {
        List<UserInput> userInputsList = userRepository.findAll();
        if (userInputsList.isEmpty()) {
            throw new ApiException("Nenhum usuário encontrado");
        }
        return userInputsList;
    }

    public UserInput listarPorId(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ApiException("Usuário não encontrado"));
    }

    public UserInput cadastrar(UserInput userInput) {
        FtpUser.salvaUsuario(userInput.getNome().toLowerCase(), userInput.getSenha());
        userRepository.save(userInput);
        return userInput;
    }

    public UserInput atualizar(UserInput userInput) {
        return this.userRepository.save(userInput);
    }

    public void remover(UserInput userInput) {
        String id = String.valueOf(userRepository.findById(userInput.getId()));
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
