package com.github.dchristofolli.dropbox.v1.file.service;

import com.github.dchristofolli.dropbox.v1.file.model.FileMapper;
import com.github.dchristofolli.dropbox.v1.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FileService {
    private UserService userService;
    private FileMapper fileMapper;

}
