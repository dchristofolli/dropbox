package com.github.dchristofolli.dropbox.controllers;
import com.github.dchristofolli.dropbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/dropbox/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity <List<User>> listarTodos(){
        return ResponseEntity.ok(this.userService.listarTodos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<User>> listarPorId(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(this.userService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity <User> cadastrar(@Valid @RequestBody User user){
        return ResponseEntity.ok (this.userService.cadastrar(user));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity <User> atualizar(@Valid @PathVariable(name = "id") String id, @RequestBody User user){
        return ResponseEntity.ok (this.userService.atualizar(user));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity <String> remover(@PathVariable(name = "id") int id){
        this.userService.remover(id);
        return  ResponseEntity.ok("");
    }

}
