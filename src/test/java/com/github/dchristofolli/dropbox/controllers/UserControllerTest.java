package com.github.dchristofolli.impl.dropbox.controllers;

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
    private com.github.dchristofolli.impl.dropbox.controllers.CreateUsers createUsers;

    @BeforeEach
    void setUp() {
        messageSource = mock(MessageSource.class);
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
        createUsers = new com.github.dchristofolli.impl.dropbox.controllers.CreateUsers();

    }

    @Test
    void listarTodos() {
        UserInput user = new UserInput();
        when(userService.showAllUsers()).
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
        userInput.setName("Teste2");
        when(userRepository.save(userInput)).thenReturn(userInput);
    }

    @Test
    void remover() {
        UserInput userInput = createUsers.criaUsuario();
        when(userRepository.findById(userInput.getId())).thenReturn(Optional.of(userInput));
        userService.deleteUser(userInput.getId());
        verify(userRepository, times(1)).deleteById(userInput.getId());
    }

    @Test
    void adicionaVisitante() {
    }
}