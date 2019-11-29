package com.github.dchristofolli.dropbox.v1.user;

import com.github.dchristofolli.dropbox.v1.exception.ApiException;
import com.github.dchristofolli.dropbox.v1.user.model.UserModel;
import com.github.dchristofolli.dropbox.v1.user.model.request.UserRequest;
import com.github.dchristofolli.dropbox.v1.user.model.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/dropbox/v1/users")
@Api("Crud de usuários do mongodb")
public class UserController {
    UserContractFacade facade;

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Exibe os dados de um usuário recebendo o ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário encontrado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = ApiException.class),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable(value = "id") String id) {
        return facade.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cadastra um novo usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário cadastrado com sucesso"),
            @ApiResponse(code = 404, message = "Dados inválidos"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest user) {
        return this.facade.createUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Atualiza os dados de um usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Dados inválidos"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @PatchMapping(path = "/{id}")
    public UserResponse update(@PathVariable(name = "id") String id,
                               @RequestBody UserRequest user) {
        return facade.update(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Exclui o cadastro de um usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário excluído"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.facade.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Permite que outro usuário tenha acesso aos arquivos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK. Seguidor adicionado"),
            @ApiResponse(code = 400, message = "Requisição inválida")
    })
    @PatchMapping
    public UserModel allowFollower(@RequestParam String idUser,
                                   @RequestParam String idFollower) {
        return facade.allowFollower(idUser, idFollower);
    }
}
