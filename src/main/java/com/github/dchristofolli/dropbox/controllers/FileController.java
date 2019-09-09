package com.github.dchristofolli.dropbox.controllers;

import com.github.dchristofolli.dropbox.services.FileService;
import com.github.dchristofolli.dropbox.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    // encodar user:password em base64 (ver qual é a classe java que faz isso) e colocar no header na hora de enviar o request
    // solucao mais simples, adicionar um header no request com o id do usuario
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
    @PostMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity envioArquivo(@RequestParam("Arquivo") MultipartFile arquivo,
                                       @PathVariable String idUsuario) {
        UserInput user = userService.listarPorId(idUsuario).get();
            fileService.enviar(arquivo, user);
            return new ResponseEntity(null, HttpStatus.ACCEPTED);
    }

    @ApiOperation("Exibe uma lista dos arquivos do usuário")
    @GetMapping("{idUsuario}")
    public ArrayList<FileInput> listaArquivos(@PathVariable String idUsuario){
        Optional<UserInput> user = userService.listarPorId(idUsuario);
        return fileService.listarArquivosDoUsuario(user.get());
    }
    @ApiOperation("Exclui um arquivo de um usuário no servidor FTP")
    @DeleteMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity excluiArquivo(@PathVariable String idUsuario, String arquivo) throws IOException {
        UserInput user = userService.listarPorId(idUsuario).get();
        fileService.deletar(arquivo, user);
        return new ResponseEntity(null, HttpStatus.OK);
    }

//    @ApiOperation("Lista de arquivos por página")
//    @GetMapping("/{idUsuario}")
//    public Page<FileInput> listaPaginada(@PathVariable String idUsuario,
//                                         @PathVariable Integer pagina,
//                                         @PathVariable Integer quantidade){
//        UserInput user = userService.listarPorId(idUsuario).get();
//        return fileService.listaPaginada(pagina, quantidade, user);
//    }

}
