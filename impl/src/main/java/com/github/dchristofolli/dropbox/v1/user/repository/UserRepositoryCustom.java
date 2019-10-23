package com.github.dchristofolli.dropbox.v1.user.repository;

import com.github.dchristofolli.dropbox.v1.user.model.UserModel;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserModel> find(UserModel model);
}
