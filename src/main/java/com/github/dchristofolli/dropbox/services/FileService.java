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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void download(String arquivo, String userId) throws FileNotFoundException {
        if(usuarioExiste(userId)){
            UserInput user = userService.listarPorId(userId).get();
            FTPClient conexao = FtpConnect.conexao(user.getNome(), user.getSenha());
            FileOutputStream local = new FileOutputStream("/Downloads");
            try {
                FTPFile[] arquivos = conexao.listFiles();
                for (FTPFile ftp : arquivos) {
                    if (arquivo.equals(ftp)) {
                        conexao.retrieveFile(arquivo, local);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean usuarioExiste(String userId) {
        Optional<List<UserInput>> users = userService.listarUsers();
        UserInput user = userService.listarPorId(userId).get();
        Optional<Boolean> userOfNullable = Optional.ofNullable(users.map(p -> p.equals(user)).isPresent());
        return false;
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
            return listarArquivosDoUsuario(null);
        }
    }

    public Page<FileInput> listaPaginada(int pagina, int quantidade, UserInput user) {
        return FtpConnect.paginacao(listarArquivosDoUsuario(user), pagina, quantidade);
    }

    public Page<FileInput> listaCompartilhadosComigo(int pagina, int quantidade, UserInput user) { // não está funcionando
        if (user.getSeguidores() != null) {
            return FtpConnect.paginacao(listarArquivosDoUsuario(userService.listarPorId(user.getSeguidores()).
                    get()), pagina, quantidade);
        }
        return null;
    }

}
