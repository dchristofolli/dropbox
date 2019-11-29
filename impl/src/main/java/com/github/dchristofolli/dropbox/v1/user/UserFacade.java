package com.github.dchristofolli.dropbox.v1.user;

import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import com.github.dchristofolli.dropbox.v1.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserFacade {
    private UserService service;

    public UserModel findById(String id) {
        return service.findById(id);
    }

    public UserModel createUser(UserModel user) {
        return service.createUser(user);
    }

    public void deleteUser(String id) {
        service.deleteUser(id);
    }

    public UserModel allowFollower(String idUser, String idFollower) {
        return service.allowFollower(idUser, idFollower);
    }

    public UserModel updateUser(String cpf) {
        return service.updateUser(cpf);
    }
}
