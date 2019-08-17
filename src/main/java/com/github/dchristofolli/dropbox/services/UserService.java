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

    public User cadastrar(User user) {
        return this.userRepository.save(user);
    }

    public List<User> listarTodos() {
        return (List<User>) this.userRepository.findAll();
    }

    public Optional<User> listarPorId(int id) {
        return this.userRepository.findById(String.valueOf(id));
    }

    public User atualizar(User user) {
        return this.userRepository.save(user);
    }

    public void remover(int id) {
        this.userRepository.deleteById(String.valueOf(id));
    }
}

