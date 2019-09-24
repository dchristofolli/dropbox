package com.github.dchristofolli.dropbox.controllers;

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
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/dropbox/users")
@Api("Crud de usuários do mongodb")
public class UserController{

    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Lista todos os usuários")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuários encontrados"),
            @ApiResponse(code = 401, message = "Acesso não autorizado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "Nenhum usuário encontrado"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @GetMapping
    public List<UserInput> listarTodos() {
        return this.userService.listarUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @NotNull
    @ApiOperation("Busca o usuário através do ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário encontrado"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @GetMapping("/id")
    public Optional<UserInput> listarPorId(@RequestParam(defaultValue = "5d80e3f880328f4fa957feb5") String id) {
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
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 204, message = "Usuário não encontrado"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> remover(@PathVariable String id) {
        this.userService.remover(id);
        return ResponseEntity.ok("Usuário removido");
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
