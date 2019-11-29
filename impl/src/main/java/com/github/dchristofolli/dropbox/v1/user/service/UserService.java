package com.github.dchristofolli.dropbox.v1.user.service;

import com.github.dchristofolli.dropbox.v1.exception.ApiException;
import com.github.dchristofolli.dropbox.v1.ftp.FtpUser;
import com.github.dchristofolli.dropbox.v1.user.mapper.UserMapperImpl;
import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import com.github.dchristofolli.dropbox.v1.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.dchristofolli.dropbox.v1.user.mapper.UserMapperImpl.mapToEntity;
import static com.github.dchristofolli.dropbox.v1.user.mapper.UserMapperImpl.mapToModel;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<UserModel> findAll() {

        List<UserModel> users = userRepository
                .findAll()
                .stream()
                .map(UserMapperImpl::mapToModel)
                .collect(Collectors.toList());
        if (users.isEmpty()) throw new ApiException("Nenhum usuário encontrado", HttpStatus.NOT_FOUND);
        return users;
    }

    public UserModel findById(String id) {
        return mapToModel(userRepository.findById(id)
                .orElseThrow(() -> new ApiException("Usuário não encontrado", HttpStatus.NOT_FOUND)));
    }

    public UserModel createUser(UserModel user) {
        FtpUser.saveUser(user.getName().toLowerCase(), user.getPassword());
        return mapToModel(userRepository.save(mapToEntity(user)));
    }

    public UserModel updateUser(String cpf) {
        UserModel userModel = mapToModel(userRepository.findByCpf(cpf).get());
        return createUser(userModel);
    }

    public void deleteUser(String id) {
        if ((!userRepository.existsById(id))) {
            throw new ApiException("Digite um usuário válido", HttpStatus.BAD_REQUEST);
        }
        userRepository.deleteById(id);
    }

    public UserModel allowFollower(String userId, String followerId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setFollower(followerId);
                    return createUser(mapToModel(user));
                }).orElseThrow(() -> new ApiException("Não encontrado", HttpStatus.NOT_FOUND));
    }
}
