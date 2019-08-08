package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.controllers.User;
import com.github.dchristofolli.dropbox.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listarUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> listarPorId(String id) {
        return this.userRepository.findById(id);
    }

    public User cadastrar(User user) {
        return this.userRepository.save(user);
    }

    public User atualizar(User user) {
        return this.userRepository.save(user);
    }

    public void remover(String id) {
        this.userRepository.deleteById(id);
    }
}
