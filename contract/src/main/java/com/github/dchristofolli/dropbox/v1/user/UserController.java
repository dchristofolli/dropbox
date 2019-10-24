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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = ApiException.class),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable(value = "id") String id) {
        return facade.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @NotNull
    @ApiOperation("Cadastra um novo usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário cadastrado com sucesso"),
            @ApiResponse(code = 404, message = "Dados inválidos"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest user) {
        return this.facade.createUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Atualiza os dados de um usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Dados inválidos"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @PatchMapping(path = "/{id}")
    public UserResponse update(@Valid @PathVariable(name = "id") String id,
                               @RequestBody UserRequest user) {
        return this.facade.update(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @NotNull
    @ApiOperation("Exclui o cadastro de um usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário excluído"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @NotNull String id) {
        this.facade.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Permite que outro usuário tenha acesso aos arquivos")
    @ApiResponses({
    })
    @PatchMapping
    public UserModel allowFollower(@RequestParam String idUser,
                                   @RequestParam String idFollower) {
        return facade.allowFollower(idUser, idFollower);
    }
}
