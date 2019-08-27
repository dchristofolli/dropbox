package com.github.dchristofolli.dropbox.controllers;

import com.github.dchristofolli.dropbox.services.FileService;
import com.github.dchristofolli.dropbox.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Optional;

@Api
@RestController
@RequestMapping("/dropbox/arquivos")
public class FileController {
    @Autowired
    UserService userService;
    @Autowired
    FileService fileService;

    @ApiOperation("Envia o arquivo para o servidor FTP")
    @PostMapping("/{id}")
    public ResponseEntity envioArquivo(@RequestParam("arquivo") MultipartFile arquivo, @PathVariable String id){
        Optional<User> user = userService.listarPorId(id);
        fileService.enviaArquivo(arquivo, user);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @ApiOperation("Exclui o arquivo do servidor FTP")
    @GetMapping("{id}")
    public ArrayList<File> listaArquivos(@PathVariable String id){
        Optional<User> user = userService.listarPorId(id);
        return fileService.listaArquivos(String.valueOf(id));
    }

}
