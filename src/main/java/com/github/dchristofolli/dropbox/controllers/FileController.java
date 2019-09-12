package com.github.dchristofolli.dropbox.controllers;

import com.github.dchristofolli.dropbox.models.FileInput;
import com.github.dchristofolli.dropbox.models.UserInput;
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

import java.io.FileNotFoundException;
import java.io.IOException;

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
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 201, message = "Arquivo enviado"),
            @ApiResponse(code = 202, message = "Arquivo enviado. Aguardando resposta do servidor"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 403, message = "Acesso restrito"),
            @ApiResponse(code = 404, message = "Página não encontrada :(")
    })
    @PostMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity envioArquivo(@RequestParam("Arquivo") MultipartFile arquivo,
                                       @PathVariable String idUsuario) {
        UserInput user = userService.listarPorId(idUsuario).get();
        fileService.enviar(arquivo, user);
        return new ResponseEntity(null, HttpStatus.ACCEPTED);
    }

    @ApiOperation("Exclui um arquivo de um usuário no servidor FTP")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Arquivo excluído"),
            @ApiResponse(code = 204, message = "Arquivo não encontrado"),
            @ApiResponse(code = 401, message = "Solicitação não autorizada"),
            @ApiResponse(code = 403, message = "Usuário não possui permissão")
    })
    @DeleteMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity excluiArquivo(@PathVariable String idUsuario, String arquivo) throws IOException {
        UserInput user = userService.listarPorId(idUsuario).get();
        fileService.deletar(arquivo, user);

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @ApiOperation("Lista paginada de arquivos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista exibida com sucesso"),
            @ApiResponse(code = 401, message = "Solicitação não autorizada"),
            @ApiResponse(code = 403, message = "Usuário não possui permissão"),
            @ApiResponse(code = 404, message = "Página não encontrada")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Page<FileInput> listaPaginada(@RequestParam(defaultValue = "1") int pagina,
                                         @RequestParam(defaultValue = "5") int quantidade,
                                         @RequestParam(defaultValue = "5d78e7cbc7d0524eba5ad341") String usuario) {
        UserInput userInput = userService.listarPorId(usuario).get();
        return fileService.listaPaginada(pagina, quantidade, userInput);
    }

    @ApiOperation("Lista paginada de arquivos compartilhados comigo")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista exibida com sucesso"),
            @ApiResponse(code = 401, message = "Solicitação não autorizada"),
            @ApiResponse(code = 403, message = "Usuário não possui permissão"),
            @ApiResponse(code = 404, message = "Página não encontrada")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/compartilhados")
    public ResponseEntity<Page<FileInput>> listaCompartilhadosComigo(@RequestParam(defaultValue = "1") int pagina,
                                         @RequestParam(defaultValue = "5") int quantidade,
                                         @RequestParam(defaultValue = "5d78e7cbc7d0524eba5ad341") String usuario) {
        UserInput userInput = userService.listarPorId(usuario).get();
            return ResponseEntity.ok(fileService.listaCompartilhadosComigo(pagina, quantidade, userInput));
    }

    @ApiOperation("Faz o download do arquivo para a máquina local")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/download")
    public ResponseEntity baixarArquivo(@RequestParam String id,
                                        @RequestParam String arquivo) throws FileNotFoundException {
        UserInput user = userService.listarPorId(id).get();
        fileService.download(user.getNome(), arquivo);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
