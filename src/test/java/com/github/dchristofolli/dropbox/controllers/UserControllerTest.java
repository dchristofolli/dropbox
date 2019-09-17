package com.github.dchristofolli.dropbox.controllers;


import com.github.dchristofolli.dropbox.models.UserInput;
import com.github.dchristofolli.dropbox.repositories.UserRepository;
import com.github.dchristofolli.dropbox.services.UserService;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.MessageSource;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Rule
    ExpectedException exception = ExpectedException.none();
    private MessageSource messageSource;
    private UserRepository userRepository;
    private UserService userService;
    private CreateUsers createUsers;

    @BeforeEach
    void setUp() {
        messageSource = mock(MessageSource.class);
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
        createUsers = new CreateUsers();

    }

    @Test
    void listarTodos() {
        UserInput user = new UserInput();
        when(userService.listarUsers()).
                thenReturn(createUsers.criaListaDeUsuarios());
    }

    @Test
    void listarPorId() {
        when(userRepository.findById(any())).
                thenReturn(Optional.of(createUsers.criaUsuario()));
    }

    @Test
    void cadastrar() {
        UserInput user = createUsers.criaUsuario();
        when(userRepository.save(user)).thenReturn(user);
        UserInput criaUsuario = userRepository.save(user);
        assertEquals(user.getId(), criaUsuario.getId());
    }

    @Test
    void atualizar() {
        UserInput userInput = createUsers.criaUsuario();
        userInput.setNome("Teste2");
        when(userRepository.save(userInput)).thenReturn(userInput);
    }

    @Test
    void remover() {
        UserInput userInput = createUsers.criaUsuario();
        when(userRepository.findById(userInput.getId())).thenReturn(Optional.of(userInput));
        userService.remover(userInput.getId());
        verify(userRepository, times(1)).deleteById(userInput.getId());
    }

    @Test
    void adicionaVisitante() {
    }
}