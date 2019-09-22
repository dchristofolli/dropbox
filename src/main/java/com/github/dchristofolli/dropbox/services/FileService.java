package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.ftp.StartServer;
import com.github.dchristofolli.dropbox.models.FileInput;
import com.github.dchristofolli.dropbox.models.UserInput;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FileService {
    @Autowired
    UserService userService;

    public void enviar(MultipartFile arquivo, UserInput userInput) {

        FTPClient conexao = FtpConnect.conexao(userInput.getNome(), userInput.getSenha());
        try {
            conexao.storeFile(arquivo.getOriginalFilename(), arquivo.getInputStream());
        } catch (IOException e) {
            e.getMessage();
        }
        StartServer.restart();
    }

    public Boolean deletar(String arquivo, UserInput user) throws IOException {
        FTPClient conexao = FtpConnect.conexao(user.getNome(), user.getSenha());
        return conexao.deleteFile(arquivo);
    }

    public void download(UserInput usuario, String arquivo) throws IOException {
        List<UserInput> users = userService.listarUsers();
        if (users.contains(usuario)) {
            FTPClient ftpClient = FtpConnect.conexao(usuario.getNome(), usuario.getSenha());
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream("/home/dchristofolli/DownloadsFTP/" + arquivo);
            ftpClient.retrieveFile(arquivo, fileOutputStream);
        }
    }

    public ArrayList<FileInput> listarArquivosDoUsuario(UserInput user) {
        FTPClient conexao = FtpConnect.conexao(user.getNome(), user.getSenha());
        try {
            FTPFile[] files = conexao.listFiles();
            ArrayList<FileInput> fileInputs = new ArrayList<>();
            for (FTPFile ftpFile : files) {
                FileInput arquivo = new FileInput(ftpFile);
                fileInputs.add(arquivo);
            }
            return fileInputs;
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

    public Page<FileInput> listaPaginada(int pagina, int quantidade, UserInput user) {
        return FtpConnect.paginacao(listarArquivosDoUsuario(user), pagina, quantidade);
    }

    public Page<FileInput> listaCompartilhadosComigo(int pagina, int quantidade, UserInput user) {
        String seguidorAux = user.getSeguidor();
        UserInput seguidor = userService.listarPorId(seguidorAux).get();
        return listaPaginada(pagina, quantidade, seguidor);
    }

    public void downloadCompartilhadosComigo(String arquivo, UserInput user) throws IOException {
        UserInput seguidor = userService.listarPorId(user.getSeguidor()).get();
        download(seguidor, arquivo);
    }
}
