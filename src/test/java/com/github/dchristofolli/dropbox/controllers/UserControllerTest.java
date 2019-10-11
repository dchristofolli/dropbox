package com.github.dchristofolli.dropbox.controllers;


import com.github.dchristofolli.dropbox.model.UserInput;
import com.github.dchristofolli.dropbox.model.UserMapper;
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
    private UserRepository userRepository;
    private UserService userService;
    private CreateUsers createUsers;
    private UserMapper userMapper;


    @BeforeEach
    void setUp() {
        MessageSource messageSource = mock(MessageSource.class);
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository, userMapper);
        createUsers = new CreateUsers();

    }

    @Test
    void showAllUsers() {
        UserInput user = new UserInput();
        when(userService.showAllUsers()).
                thenReturn(createUsers.createUsersList());
    }

    @Test
    void findById() {
        when(userRepository.findById(any())).
                thenReturn(Optional.of(createUsers.createUser()));
    }

    @Test
    void setCreateUsers() {
        UserInput user = createUsers.createUser();
        when(userRepository.save(user)).thenReturn(user);
        UserInput createUser = userRepository.save(user);
        assertEquals(user.getId(), createUser.getId());
    }

    @Test
    void updateUser() {
        UserInput userInput = createUsers.createUser();
        userInput.setName("Test2");
        when(userRepository.save(userInput)).thenReturn(userInput);
    }

    @Test
    void deleteById() {
        UserInput userInput = createUsers.createUser();
        when(userRepository.findById(userInput.getId())).thenReturn(Optional.of(userInput));
        userService.deleteUser(userInput);
        verify(userRepository, times(1)).deleteById(userInput.getId());
    }

    @Test
    void addFollower() {
    }
}