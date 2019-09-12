package com.github.dchristofolli.dropbox.controllers;

import com.github.dchristofolli.dropbox.models.UserInput;
import com.github.dchristofolli.dropbox.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/dropbox/users")
@Api("Crud de usuários do mongodb")
public class UserController {
    @Autowired
    private UserService userService;


    @ApiOperation("Lista todos os usuários")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuários encontrados"),
            @ApiResponse(code = 401, message = "Acesso não autorizado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "Nenhum usuário encontrado"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @GetMapping
    public ResponseEntity<List<UserInput>> listarTodos() {
        return ResponseEntity.ok(this.userService.listarUsers());
    }

    @ApiOperation("Busca o usuário através do ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário encontrado"),
            @ApiResponse(code = 401, message = "Usuário inválido"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @GetMapping(path = "/{id}")
    public Optional<UserInput> listarPorId(@PathVariable(name = "id") String id) {
        return userService.listarPorId(id);
    }

    @ApiOperation("Cadastra um novo usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 201, message = "Usuário cadastrado com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "Dados inválidos"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @PostMapping
    public ResponseEntity<UserInput> cadastrar(@Valid @RequestBody UserInput userInput) {
        return ResponseEntity.ok(this.userService.cadastrar(userInput));
    }

    @ApiOperation("Atualiza os dados de um usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 201, message = "Usuário atualizado com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "Dados inválidos"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @PutMapping(path = "/{id}")
    public ResponseEntity<UserInput> atualizar(@Valid @PathVariable(name = "id") String id,
                                               @RequestBody UserInput userInput) {
        return ResponseEntity.ok(this.userService.atualizar(userInput));
    }

    @ApiOperation("Exclui o cadastro de um usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 204, message = "Usuário não encontrado"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor")})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> remover(@PathVariable(name = "id") String id) {
        this.userService.remover(id);
        return ResponseEntity.ok("Usuário removido");
    }

//    @ApiOperation("Permite que outro usuário tenha acesso aos arquivos")
//    @ApiResponses({
//    })
//    @PutMapping("/{idUsuario}/{idVisitante}")
//    public ResponseEntity<UserInput> adicionaVisitante(@PathVariable String idUsuario,
//                                                       @PathVariable String idVisitante) {
//        return ResponseEntity.ok(userService.permiteVisitante(idUsuario, idVisitante));
//    }
}
