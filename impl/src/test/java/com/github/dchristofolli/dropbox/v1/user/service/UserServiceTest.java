package com.github.dchristofolli.dropbox.v1.user.service;

import com.github.dchristofolli.dropbox.v1.exception.ApiException;
import com.github.dchristofolli.dropbox.v1.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository repository;
    @InjectMocks
    UserService service;

    @Test(expected = ApiException.class)
    public void findAll_null() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        service.findAll();
    }

//    @Test
//    void findById() {
//    }
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