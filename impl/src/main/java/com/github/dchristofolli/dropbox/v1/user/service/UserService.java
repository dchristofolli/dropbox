package com.github.dchristofolli.dropbox.v1.user.service;

import com.github.dchristofolli.dropbox.v1.exception.ApiException;
import com.github.dchristofolli.dropbox.v1.user.repository.UserEntity;
import com.github.dchristofolli.dropbox.v1.user.repository.UserRepository;
import com.github.dchristofolli.dropbox.v1.ftp.FtpUser;
import com.github.dchristofolli.dropbox.v1.user.mapper.UserMapperImpl;
import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<UserModel> findAll() {
        List<UserModel> users = userRepository
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

    public UserModel findByCpf(String cpf) {
        UserModel user = null;
        List<UserModel> list = findAll();
        for (UserModel u : list) {
            if (!u.getCpf().equals(cpf)) {
                throw new ApiException("Usuário não encontrado", HttpStatus.NOT_FOUND);
            }
            user = findById(u.getId());
        }
        return user;
    }

    public UserModel createUser(UserModel user) {
        FtpUser.saveUser(user.getName().toLowerCase(), user.getPassword());
        return mapToModel(userRepository.save(UserMapperImpl.mapToEntity(user)));
    }

    public UserModel updateUser(UserModel user) {
        return mapToModel(userRepository.save(UserMapperImpl.mapToEntity(user)));
    }

    public void deleteUser(UserModel user) {
        if (!userRepository.existsById(user.getId())) {
            throw new ApiException("Usuário não existe", HttpStatus.BAD_REQUEST);
        }
        userRepository.deleteById(user.getId());
    }

    public UserModel allowFollower(String userId, String followerId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setFollower(followerId);
                    return updateUser(mapToModel(user));
                }).orElseThrow(() -> new ApiException("Não encontrado", HttpStatus.NOT_FOUND));
    }

    private UserModel mapToModel(UserEntity user) {
        return UserMapperImpl.mapToModel(user);
    }

}
