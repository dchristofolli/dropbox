package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.ftp.FtpUser;
import com.github.dchristofolli.dropbox.models.UserInput;
import com.github.dchristofolli.dropbox.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserInput> listarUsers() {
        return this.userRepository.findAll();
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

    public void remover(String id) {
        this.userRepository.deleteById(id);
    }

//    public UserInput permiteVisitante(String idUsuario, String idVisitante) {
//        if(userRepository.existsById(idUsuario)){
//            Optional<UserInput> userInput = userRepository.findById(idUsuario);
//            UserInput user = userInput.get();
//            user.getSeguidores().add(idVisitante);
//            return atualizar(user);
//        }
//        return null;
//    }
}
