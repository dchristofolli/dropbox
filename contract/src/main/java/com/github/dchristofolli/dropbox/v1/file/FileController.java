package com.github.dchristofolli.dropbox.v1.file;

import com.github.dchristofolli.dropbox.v1.file.model.FileMapper;
import com.github.dchristofolli.dropbox.v1.file.model.FileModelRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Api
@AllArgsConstructor
@RestController
@RequestMapping("/dropbox/arquivos")
public class FileController {
    FileContractFacade fileFacade;
<<<<<<< HEAD
=======

>>>>>>> c36e740bfccbbe49aeca563585c738fd3efe0afb
    @ApiOperation("Envia o arquivo para o servidor FTP")
    @ApiResponses({
            @ApiResponse(code = 226, message = "Arquivo enviado"),
            @ApiResponse(code = 202, message = "Arquivo enviado. Aguardando resposta do servidor"),
            @ApiResponse(code = 403, message = "Acesso restrito"),
            @ApiResponse(code = 404, message = "Página não encontrada :(")
    })
    @PostMapping("/files")
    @ResponseStatus(HttpStatus.IM_USED)
    public void sendFile(@RequestParam MultipartFile file,
                         @RequestParam String userId) {
        fileFacade.sendFile(file, userId);
    }

    @ApiOperation("Exclui um arquivo de um usuário no servidor FTP")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Arquivo excluído"),
            @ApiResponse(code = 403, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado")
    })
    @DeleteMapping("/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String idUser, String fileName) {
        fileFacade.delete(idUser, fileName);
    }

    @ApiOperation("Lista paginada de arquivos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista exibida com sucesso"),
            @ApiResponse(code = 403, message = "Não permitido"),
            @ApiResponse(code = 404, message = "Página não encontrada")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
<<<<<<< HEAD
    public Page<FileModel> pagedList(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "5") int quantity,
                                     @RequestParam String user) {
        return fileFacade.pagedList(page, quantity, user);
=======
    public Page<FileMapper> pagedList(@RequestParam FileModelRequest fileModelRequest) {
        return fileFacade.pagedList(fileModelRequest);
>>>>>>> c36e740bfccbbe49aeca563585c738fd3efe0afb
    }

    @ApiOperation("Lista paginada de arquivos compartilhados comigo")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista exibida com sucesso"),
            @ApiResponse(code = 403, message = "Usuário não possui permissão"),
            @ApiResponse(code = 404, message = "Página não encontrada")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/compartilhados")
    public Page<FileMapper> listsSharedWithMe(@RequestParam FileModelRequest request) {
        return fileFacade.listsSharedWithMe(request);
    }

    @ApiOperation("Faz o download do arquivo para a máquina local")
    @NotNull
    @ApiResponses({
            @ApiResponse(code = 200, message = "Arquivo baixado com sucesso"),
            @ApiResponse(code = 403, message = "Operação não permitida para o usuário"),
            @ApiResponse(code = 404, message = "Página não encontrada")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/download")
    public void download(@RequestParam String id,
                         @RequestParam String file) {
        fileFacade.download(id, file);
    }

    @ApiOperation("Baixa um arquivo compartilhado comigo")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Arquivo baixado com sucesso"),
            @ApiResponse(code = 403, message = "Operação não permitida para o usuário"),
            @ApiResponse(code = 404, message = "Página não encontrada")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("compartilhadoComigo/download")
    public void downloadSharedWithMe(@RequestParam String id,
                                     @RequestParam String file) {
        fileFacade.downloadSharedWithMe(id, file);
    }
}
