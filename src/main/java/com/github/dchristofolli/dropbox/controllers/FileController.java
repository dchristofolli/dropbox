package com.github.dchristofolli.dropbox.controllers;

import com.github.dchristofolli.dropbox.models.FileInput;
import com.github.dchristofolli.dropbox.models.UserInput;
import com.github.dchristofolli.dropbox.services.FileService;
import com.github.dchristofolli.dropbox.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@Api
@RestController
@RequestMapping("/dropbox/arquivos")
public class FileController {
    // encodar user:password em base64 (ver qual é a classe java que faz isso) e colocar no header na hora de enviar o request
    // solucao mais simples, adicionar um header no request com o id do usuario
    UserService userService;
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
    @PostMapping("/arquivos")
    @ResponseStatus(HttpStatus.CREATED)
    public void envioArquivo(@RequestParam MultipartFile arquivo,
                                       @RequestParam String idUsuario) {
        UserInput user = userService.listarPorId(idUsuario).get();
        fileService.enviar(arquivo, user);
    }

    @ApiOperation("Exclui um arquivo de um usuário no servidor FTP")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Arquivo excluído"),
            @ApiResponse(code = 204, message = "Arquivo não encontrado"),
            @ApiResponse(code = 401, message = "Solicitação não autorizada"),
            @ApiResponse(code = 403, message = "Usuário não possui permissão"),
            @ApiResponse(code = 404, message = "Usuário não encontrado")
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
    @GetMapping
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
    @GetMapping("/compartilhados")
    public Page<FileInput> listaCompartilhadosComigo(@RequestParam(defaultValue = "1") int pagina,
                                                                     @RequestParam(defaultValue = "5") int quantidade,
                                                                     @RequestParam(defaultValue = "5d80e38e80328f4fa957feb4") String usuario) {
        UserInput userInput = userService.listarPorId(usuario).get();
        return fileService.listaCompartilhadosComigo(pagina, quantidade, userInput);
    }

    @ApiOperation("Faz o download do arquivo para a máquina local")
    @NotNull
    @ApiResponses({
            @ApiResponse(code = 200, message = "Arquivo baixado com sucesso"),
            @ApiResponse(code = 401, message = "Download não autorizado"),
            @ApiResponse(code = 403, message = "Operação não permitida para o usuário"),
            @ApiResponse(code = 404, message = "Página não encontrada")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/download")
    public void baixarArquivo(@RequestParam String id,
                                        @RequestParam String arquivo) throws IOException {
        UserInput user = userService.listarPorId(id).get();
        fileService.download(user, arquivo);
    }

    @ApiOperation("Baixa um arquivo compartilhado comigo")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Arquivo baixado com sucesso"),
            @ApiResponse(code = 401, message = "Download não autorizado"),
            @ApiResponse(code = 403, message = "Operação não permitida para o usuário"),
            @ApiResponse(code = 404, message = "Página não encontrada")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("compartilhadoComigo/download")
    public void baixarCompartilhadoComigo(@RequestParam String id,
                                                    @RequestParam String arquivo) throws IOException {
        UserInput userInput = userService.listarPorId(id).get();
        fileService.downloadCompartilhadosComigo(arquivo, userInput);
    }
}
