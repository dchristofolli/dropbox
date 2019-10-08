package com.github.dchristofolli.file.service;

import com.github.dchristofolli.contract.v1.user.model.response.UserResponse;
import com.github.dchristofolli.file.model.FileInput;
import com.github.dchristofolli.ftp.FtpConnect;
import com.github.dchristofolli.ftp.FtpStart;
import com.github.dchristofolli.user.exception.ApiException;
import com.github.dchristofolli.user.mapper.UserMapper;
import com.github.dchristofolli.user.model.UserInput;
import com.github.dchristofolli.user.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FileService {
    private UserService userService;
    private UserMapper userMapper;

    public void sendFile(MultipartFile file, UserInput userInput) {

        FTPClient conexao = FtpConnect.connect(userInput.getName(), userInput.getPassword());
        try {
            conexao.storeFile(file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            e.getMessage();
        }
        FtpStart.restart();
    }

    public Boolean deleteFile(String arquivo, UserInput user) throws IOException { //TODO revisar esse método
        FTPClient conexao = FtpConnect.connect(user.getName(), user.getPassword());
        return conexao.deleteFile(arquivo);
    }

    public void downloadFile(UserInput user, String fileName) throws IOException {
        List<UserResponse> users = userService.showAllUsers();
        UserResponse userResponse = userMapper.userInputToUserResponse(user);
        if (users.contains(userResponse)) {
            FTPClient ftpClient = FtpConnect.connect(user.getName(), user.getPassword());
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream("/home/dchristofolli/DownloadsFTP/" + fileName);
            ftpClient.retrieveFile(fileName, fileOutputStream);
        }
    }

    public ArrayList<FileInput> listUserFiles(UserInput user) {
        FTPClient conexao = FtpConnect.connect(user.getName(), user.getPassword());
        try {
            FTPFile[] files = conexao.listFiles();
            ArrayList<FileInput> fileInputs = new ArrayList<>();
            for (FTPFile ftpFile : files) {
                FileInput fileInput = new FileInput(ftpFile);
                fileInputs.add(fileInput);
            }
            return fileInputs;
        } catch (IOException e) {
            e.getMessage();
            throw new ApiException("Arquivo não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public Page<FileInput> filesPagedList(int quantidade, UserInput user) {
        return FtpConnect.page(listUserFiles(user));
    }

    public Page<FileInput> listsSharedWithMe(int pagina, int quantidade, UserInput user) {
        //TODO revisar esse método
        String getFollower = user.getFollower();
        Optional<UserInput> follower = userService.getUserById(getFollower);
        return filesPagedList(quantidade, follower.get());
    }

    public void downloadSharedWithMe(String arquivo, UserInput user) throws IOException {
        // TODO testar no swagger
        UserResponse response = userService.showUserById(user.getId());
        UserInput input = userMapper.userInputToUserResponse(response);
        downloadFile(input, arquivo);
    }
}
