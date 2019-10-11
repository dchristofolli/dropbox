package com.github.dchristofolli.file;

import com.github.dchristofolli.dropbox.file.model.FileModel;

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

@Api
@AllArgsConstructor
@RestController
@RequestMapping("/dropbox/arquivos")
public class FileController {
    // encodar user:password em base64 (ver qual é a classe java que faz isso) e colocar no header na hora de enviar o request
    // solucao mais simples, adicionar um header no request com o id do usuario
    FileContractFacade fileFacade;

    @ApiOperation("Envia o arquivo para o servidor FTP")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 201, message = "Arquivo enviado"),
            @ApiResponse(code = 202, message = "Arquivo enviado. Aguardando resposta do servidor"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 403, message = "Acesso restrito"),
            @ApiResponse(code = 404, message = "Página não encontrada :(")
    })
    @PostMapping("/files")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendFile(@RequestParam MultipartFile file,
                         @RequestParam String userId) {
        fileFacade.sendFile(file, userId);
    }

    @ApiOperation("Exclui um arquivo de um usuário no servidor FTP")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Arquivo excluído"),
            @ApiResponse(code = 204, message = "Arquivo não encontrado"),
            @ApiResponse(code = 401, message = "Solicitação não autorizada"),
            @ApiResponse(code = 403, message = "Usuário não possui permissão"),
            @ApiResponse(code = 404, message = "Usuário não encontrado")
    })
    @DeleteMapping("/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity delete(@PathVariable String idUser, String fileName) throws IOException {
        return fileFacade.delete(idUser, fileName);
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
    public Page<FileModel> pagedList(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "5") int quantity,
                                     @RequestParam(defaultValue = "5d78e7cbc7d0524eba5ad341") String user) {
        ;
        return fileFacade.pagedList(page, quantity, user);
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
    public Page<FileModel> listsSharedWithMe(@RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "5") int quantity,
                                             @RequestParam(defaultValue = "5d80e38e80328f4fa957feb4") String user) {
        return fileFacade.listsSharedWithMe(page, quantity, user);
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
    public void download(@RequestParam String id,
                         @RequestParam String file) throws IOException {
        fileFacade.download(id, file);
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
    public void downloadSharedWithMe(@RequestParam String id,
                                     @RequestParam String file) throws IOException {
        fileFacade.downloadSharedWithMe(id, file);
    }
}
