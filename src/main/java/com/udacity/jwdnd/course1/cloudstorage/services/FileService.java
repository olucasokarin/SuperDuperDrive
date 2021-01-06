package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;
    private final UserService userService;

    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    public List<File> getFiles() {
        User user = userService.getCurrentUser();
        return fileMapper.getFiles(user.getUserId());
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public int createFile(MultipartFile multipartFile) throws IOException {
        User user = userService.getCurrentUser();

        String filename = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        String fileSize = String.valueOf(multipartFile.getSize());

        File file = new File(null, filename, contentType, fileSize, user.getUserId(), multipartFile.getBytes());
        return fileMapper.insert(file);
    }

    public void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }
}
