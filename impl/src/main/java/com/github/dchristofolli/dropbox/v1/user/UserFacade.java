package com.github.dchristofolli.dropbox.v1.user;

import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import com.github.dchristofolli.dropbox.v1.user.model.UserModelList;
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

    public UserModelList findAll() {
        return service.findAll();
    }

    public UserModel createUser(UserModel user) {
        return service.createUser(user);
    }

    public void deleteUser(UserModel user) {
        service.deleteUser(user);
    }

    public UserModel allowFollower(String idUser, String idFollower) {
        return service.allowFollower(idUser, idFollower);
    }

    public UserModel updateUser(UserModel user) {
        return service.updateUser(user);
    }
}
