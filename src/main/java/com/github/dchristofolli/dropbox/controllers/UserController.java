package com.github.dchristofolli.dropbox.controllers;

import com.github.dchristofolli.dropbox.exceptions.ApiException;
import com.github.dchristofolli.dropbox.models.UserInput;
import com.github.dchristofolli.dropbox.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/dropbox/v1/users")
@Api("Crud de usuários do mongodb")
public class UserController {

    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Lista todos os usuários")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuários encontrados", response = UserInput.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Acesso não autorizado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "Nenhum usuário encontrado", response = ApiException.class),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @GetMapping
    public List<UserInput> listarTodos() {
        return this.userService.listarUsers();
    }//TODO criar models de response request

    @ResponseStatus(HttpStatus.OK)
    @NotNull
    @ApiOperation("Busca o usuário através do ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário encontrado"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = ApiException.class),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @GetMapping("/id")
    public UserInput listarPorId(@RequestParam String id) {
        return userService.listarPorId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @NotNull
    @ApiOperation("Cadastra um novo usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 201, message = "Usuário cadastrado com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "Dados inválidos"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @PostMapping
    public UserInput cadastrar(@Valid @RequestBody UserInput userInput) {
        return this.userService.cadastrar(userInput);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @NotNull
    @ApiOperation("Atualiza os dados de um usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 201, message = "Usuário atualizado com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "Dados inválidos"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @PutMapping(path = "/{id}")
    public UserInput atualizar(@Valid @PathVariable(name = "id") String id,
                               @RequestBody UserInput userInput) {
        return this.userService.atualizar(userInput);
    }

    @ResponseStatus(HttpStatus.OK)
    @NotNull
    @ApiOperation("Exclui o cadastro de um usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário excluído"),
            @ApiResponse(code = 204, message = "Usuário não encontrado"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @DeleteMapping(path = "/{user}")
    public ResponseEntity<UserInput> remover(@PathVariable @NotNull UserInput user) {
        this.userService.remover(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @NotNull
    @ApiOperation("Permite que outro usuário tenha acesso aos arquivos")
    @ApiResponses({
    })
    @PutMapping
    public UserInput adicionaVisitante(@RequestParam String idUsuario,
                                       @RequestParam String idVisitante) {
        return userService.permiteVisitante(idUsuario, idVisitante);
    }
}
