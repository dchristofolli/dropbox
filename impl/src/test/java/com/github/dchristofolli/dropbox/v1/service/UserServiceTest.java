package com.github.dchristofolli.dropbox.v1.service;

import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import com.github.dchristofolli.dropbox.v1.user.service.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("UserService Test")
public class UserServiceTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private UserService userService;

    @Before
    public void setup() {
        userService = mock(UserService.class);
    }

    @Test
    public void createUser() {
        UserModel user = CreateUsers.createUser();
        when(userService.createUser(user)).thenReturn(user);
        UserModel createUser = userService.createUser(user);
        assertEquals(user.getId(), createUser.getId());
    }

    @Test
    public void showAllUsers() {
        when(userService.findAll()).
                thenReturn(CreateUsers.createUsersList());
    }

    @Test
    public void findById() {
        when(userService.findById(any())).
                thenReturn(CreateUsers.createUser());
    }


    @Test
    public void updateUser() {
        UserModel user = CreateUsers.createUser();
        user.setName("Test2");
        when(userService.createUser(user)).thenReturn(user);
    }

    @Test
    public void deleteById() {
        UserModel user = CreateUsers.createUser();
        when(userService.findById(user.getId())).thenReturn(user);
        userService.deleteUser(user);
        verify(userService, times(1)).deleteUser(user);
    }

    @Test
    public void addFollower() {
        UserModel user = CreateUsers.createUser();
        UserModel follower = CreateUsers.createUser();
        when(userService.allowFollower(user.getId(), follower.getId())).thenReturn(user, follower);
    }

}
