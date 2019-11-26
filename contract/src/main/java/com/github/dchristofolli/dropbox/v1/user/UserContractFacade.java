package com.github.dchristofolli.dropbox.v1.user;

import com.github.dchristofolli.dropbox.v1.user.mapper.UserMapperContract;
import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import com.github.dchristofolli.dropbox.v1.user.model.request.UserRequest;
import com.github.dchristofolli.dropbox.v1.user.model.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class UserContractFacade {
    private UserFacade facade;

    UserResponse findById(String id) {
        return UserMapperContract.ImplMapUserModelToUserResponse(facade.findById(id));
    }

    UserResponse createUser(UserRequest user) {
        return UserMapperContract.ImplMapUserModelToUserResponse(facade.createUser(UserMapperContract
                .implMapUserRequestToUserModel(user)));
    }

    UserResponse update(UserRequest user) {
        return UserMapperContract.ImplMapUserModelToUserResponse(facade.updateUser(UserMapperContract
                .implMapUserRequestToUserModel(user)));
    }

    void deleteUser(String id) {
        facade.deleteUser(id);
    }

    UserModel allowFollower(String idUser, String idFollower) {
        return facade.allowFollower(idUser, idFollower);
    }
}
