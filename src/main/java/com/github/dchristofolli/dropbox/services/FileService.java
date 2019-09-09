package com.github.dchristofolli.dropbox.services;

import com.github.dchristofolli.dropbox.controllers.FileInput;
import com.github.dchristofolli.dropbox.controllers.UserInput;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class FileService {
    @Autowired
    UserService userService;

    public void enviar(MultipartFile arquivo, UserInput userInput){

        FTPClient conexao = ServiceUtil.conexao(userInput.getNome(), userInput.getSenha());
        try{
            conexao.storeFile(arquivo.getOriginalFilename(), arquivo.getInputStream());
        } catch (IOException e){
            e.getMessage();
        }

    }

    public Boolean deletar(String arquivo,UserInput user) throws IOException {
        FTPClient conexao = ServiceUtil.conexao(user.getNome(), user.getSenha());
        return conexao.deleteFile(arquivo);
    }

    public ArrayList<FileInput> listarArquivosDoUsuario(UserInput user) {
        FTPClient conexao = ServiceUtil.conexao(user.getNome(), user.getSenha());
        try{
            FTPFile[] files = conexao.listFiles();
            ArrayList<FileInput> fileInputs = new ArrayList<>();
            for(FTPFile ftpFile : files){
                FileInput arquivo = new FileInput(ftpFile);
                fileInputs.add(arquivo);
        }
            return fileInputs;
        } catch (IOException e){
            e.getMessage();
            return listarArquivosDoUsuario(null);
        }
    }

//    public Page<FileInput> listaPaginada(int pagina, int quantidade, UserInput usuario) {
//        FTPClient conexao = ServiceUtil.conexao(usuario.getNome(),usuario.getSenha());
//        return ServiceUtil.pagina(listarArquivosDoUsuario(usuario), pagina, quantidade);
//    }
}
