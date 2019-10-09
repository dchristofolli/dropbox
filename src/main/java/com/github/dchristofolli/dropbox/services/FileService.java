package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.ftp.StartServer;
import com.github.dchristofolli.dropbox.models.FileInput;
import com.github.dchristofolli.dropbox.models.UserInput;
import com.github.dchristofolli.dropbox.models.UserMapper;
import com.github.dchristofolli.dropbox.models.UserResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class FileService {
    private UserService userService;
    private UserMapper userMapper;

    public void enviar(MultipartFile arquivo, UserInput userInput) {

        FTPClient conexao = FtpConnect.conexao(userInput.getName(), userInput.getPassword());
        try {
            conexao.storeFile(arquivo.getOriginalFilename(), arquivo.getInputStream());
        } catch (IOException e) {
            e.getMessage();
        }
        StartServer.restart();
    }

    public Boolean deletar(String arquivo, UserInput user) throws IOException {
        FTPClient conexao = FtpConnect.conexao(user.getName(), user.getPassword());
        return conexao.deleteFile(arquivo);
    }

    public void download(UserInput user, String arquivo) throws IOException {
        List<UserResponse> users = userService.showAllUsers();
        UserResponse userResponse = userMapper.userResponseMapper(user);
        if (users.contains(userResponse)) {
            FTPClient ftpClient = FtpConnect.conexao(user.getName(), user.getPassword());
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream("/home/dchristofolli/DownloadsFTP/" + arquivo);
            ftpClient.retrieveFile(arquivo, fileOutputStream);
        }
    }

    public ArrayList<FileInput> listarArquivosDoUsuario(UserInput user) {
        FTPClient conexao = FtpConnect.conexao(user.getName(), user.getPassword());
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

//    public Page<FileInput> listaCompartilhadosComigo(int pagina, int quantidade, UserInput user) {
//        String seguidorAux = user.getFollower();
//        UserInput seguidor = userMapper.userInputMapper(userService.showUserById(user));
//        return listaPaginada(pagina, quantidade, seguidor);
//    }
//
//    public void downloadCompartilhadosComigo(String arquivo, UserInput user) throws IOException {
//        UserInput seguidor = userService.listarPorId(user.getSeguidor());
//        download(seguidor, arquivo);
//    }
}
