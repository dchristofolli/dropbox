package com.github.dchristofolli.dropbox.controllers;

import com.github.dchristofolli.dropbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/dropbox/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listarTodos() {
        return ResponseEntity.ok(this.userService.listarUsers());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> listarPorId(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(this.userService.listarPorId(id).get());
    }

    @PostMapping
    public ResponseEntity<User> cadastrar(@Valid @RequestBody User user) {
        return ResponseEntity.ok(this.userService.cadastrar(user));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> atualizar(@Valid @PathVariable(name = "id") String id, @RequestBody User user) {
        return ResponseEntity.ok(this.userService.atualizar(user));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> remover(@PathVariable(name = "id") String id) {
        this.userService.remover(id);
        return ResponseEntity.ok("");
    }

}
