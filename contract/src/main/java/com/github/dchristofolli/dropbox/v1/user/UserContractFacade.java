package com.github.dchristofolli.dropbox.v1.user;

import com.github.dchristofolli.dropbox.v1.user.mapper.UserMapperContract;
import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import com.github.dchristofolli.dropbox.v1.user.model.UserModelList;
import com.github.dchristofolli.dropbox.v1.user.model.request.UserRequest;
import com.github.dchristofolli.dropbox.v1.user.model.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class UserContractFacade {
    private UserFacade facade;

    UserResponse findById(String id) {
        return UserMapperContract.mapToContract(facade.findById(id));
    }

    UserModelList findAllUsers() {
        return facade.findAll();
    }

    UserResponse createUser(UserRequest user) {
        return UserMapperContract.mapToContract(facade.createUser(UserMapperContract.mapToImpl(user)));
    }

    UserResponse update(UserRequest user) {
        return UserMapperContract.mapToContract(facade.updateUser(UserMapperContract.mapToImpl(user)));
    }

    void deleteUser(UserModel user) {
        facade.deleteUser(user);
    }

    UserModel allowFollower(String idUser, String idFollower) {
        return facade.allowFollower(idUser, idFollower);
    }
}