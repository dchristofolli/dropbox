package com.github.dchristofolli.dropbox.v1.user;

import com.github.dchristofolli.dropbox.v1.user.repository.UserRepository;
import com.github.dchristofolli.dropbox.v1.user.stub.UserStub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @MockBean
    UserRepository userRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void findById() throws Exception {
        given(userRepository.findById("13454")).willReturn(java.util.Optional.of(UserStub.userEntityStub()));
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