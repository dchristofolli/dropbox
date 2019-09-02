package com.github.dchristofolli.dropbox.controllers;

import com.github.dchristofolli.dropbox.services.FileService;
import com.github.dchristofolli.dropbox.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @ApiResponses({
            @ApiResponse(code=200, message = "Ok"),
            @ApiResponse(code=201, message = "Arquivo enviado"),
            @ApiResponse(code=202, message = "Alguma coisa deu errado"),
            @ApiResponse(code=401, message = "Não autorizado"),
            @ApiResponse(code=403, message = "Acesso restrito"),
            @ApiResponse(code=404, message = "Página não encontrada :(")
    })
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity envioArquivo(@RequestParam("Arquivo") MultipartFile arquivo, @PathVariable String id)
            throws IOException {
        UserInput user = userService.listarPorId(id).get();
            fileService.enviar(arquivo, user);
            return new ResponseEntity(null, HttpStatus.ACCEPTED);
    }

    @ApiOperation("Exibe uma lista dos arquivos do usuário")
    @GetMapping("{id}")
    public ArrayList<FileInput> listaArquivos(@PathVariable String id){
        Optional<UserInput> user = userService.listarPorId(id);
        try {
            return fileService.listar(user.get());
        } catch (IOException e) {
            e.getMessage();
        }
        return null;
    }

}
