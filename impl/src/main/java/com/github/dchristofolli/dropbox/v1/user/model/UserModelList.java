package com.github.dchristofolli.dropbox.v1.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserModelList {
    List<UserModel> userModels;

    public boolean isEmpty() {
        return userModels.isEmpty();
    }

    public boolean contains(String userResponse) {
        return userModels.contains(userResponse);
    }

    public void add(UserModel u) {
        userModels.add(u);
    }
}
