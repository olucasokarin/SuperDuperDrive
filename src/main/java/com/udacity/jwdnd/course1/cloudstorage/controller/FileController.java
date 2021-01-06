package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "/{id}")
    public HttpEntity getFile(@PathVariable("id") Integer id) {
        File file = fileService.getFile(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(file.getContentType()));

        return new HttpEntity(file.getFiledata(), headers);
    }

    @PostMapping()
    public String fileCreate(@RequestParam("fileUpload") MultipartFile multipartFile, Model model){

        try {
            fileService.createFile(multipartFile);
            model.addAttribute("result", "success");
        }catch (IOException ioException) {
            model.addAttribute("result", "error");
        }

        return "result";
    }

    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable("id") Integer id, Model model) {
        fileService.deleteFile(id);
        model.addAttribute("result", "success");

        return "result";
    }
}
