package com.github.dchristofolli.dropbox.user.service;

import com.github.dchristofolli.dropbox.exception.ApiException;
import com.github.dchristofolli.dropbox.file.model.FileModel;
import com.github.dchristofolli.dropbox.ftp.FtpUser;
import com.github.dchristofolli.dropbox.user.mapper.UserMapperImpl;
import com.github.dchristofolli.dropbox.user.model.UserModel;
import com.github.dchristofolli.dropbox.user.model.UserModelList;
import com.github.dchristofolli.dropbox.user.repository.UserEntity;
import com.github.dchristofolli.dropbox.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserModelList findAll() {
        UserModelList users = (UserModelList) userRepository
                .findAll()
                .stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
        if (users.isEmpty()) throw new ApiException("Nenhum usuário encontrado", HttpStatus.NOT_FOUND);
        return users;
    }

    public UserModel findById(String id) {
        return mapToModel(userRepository.findById(id)
                .orElseThrow(() -> new ApiException("Usuário não encontrado", HttpStatus.NOT_FOUND)));
    }

    public UserModel createUser(UserModel user) {
        FtpUser.salvaUsuario(user.getName().toLowerCase(), user.getPassword());
        return mapToModel(userRepository.save(UserMapperImpl.mapToEntity(user)));
    }

    public UserModel updateUser(UserModel user) {
        return mapToModel(userRepository.save(UserMapperImpl.mapToEntity(user)));
    }

    public void deleteUser(UserModel user) {
        this.userRepository.deleteById(user.getId());
    }

    public UserModel allowFollower(String userId, String followerId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setFollower(followerId);
                    return updateUser(mapToModel(user));
                }).orElseThrow(() -> new ApiException("Não encontrado", HttpStatus.NOT_FOUND));
    }

    public UserModel mapToModel(UserEntity user) {
        return UserMapperImpl.mapToModel(user);
    }

}
