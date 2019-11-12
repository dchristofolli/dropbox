package com.github.dchristofolli.dropbox.v1.user;

import com.github.dchristofolli.dropbox.v1.user.repository.UserEntity;
import com.github.dchristofolli.dropbox.v1.user.repository.UserRepository;
import com.github.dchristofolli.dropbox.v1.user.stub.UserStub;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Mock
    UserRepository userRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void findById() throws Exception {
        UserEntity userModel = UserStub.userEntityStub();
        given(userRepository.findById(userModel.getId())).willReturn(Optional.of(userModel));
        this.mockMvc.perform(get("/dropbox/v1/users/13454")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("13454"));
    }

//    @Test
//    public void createUser() {
//    }
//
//    @Test
//    public void update() {
//    }
//
//    @Test
//    public void delete() {
//    }
//
//    @Test
//    public void allowFollower() {
//    }
}