package com.github.dchristofolli.dropbox.v1.user.service;

import com.github.dchristofolli.dropbox.v1.user.repository.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    UserRepository repository;
    @InjectMocks
    UserService service;

    @Test
    public void findAll_returns_api_exception_when_the_list_is_empty() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Nenhum usuário encontrado");
        when(repository.findAll()).thenReturn(Collections.emptyList());
        service.findAll();
    }

    @Test
    void findById() {

    }
//
//    @Test
//    void createUser() {
//    }
//
//    @Test
//    void updateUser() {
//    }
//
//    @Test
//    void deleteUser() {
//    }
//
//    @Test
//    void allowFollower() {
//    }
}