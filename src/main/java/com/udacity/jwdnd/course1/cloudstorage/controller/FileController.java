package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "/{id}")
    public void getFile(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException{
        File file = fileService.getFile(id);

        if(file != null) {
            String mimeType = file.getContentType();

            if(mimeType == null)
                mimeType = "application/octet-stream";

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getFilename() + "\""));
            response.setContentLength(Integer.valueOf(file.getFileSize()));

            FileCopyUtils.copy(file.getFiledata(), response.getOutputStream());
        }
    }

//    @GetMapping(value = "/{id}")
//    public HttpEntity getFile(@PathVariable("id") Integer id) {
//        File file = fileService.getFile(id);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.valueOf(file.getContentType()));
//
//        return new HttpEntity(file.getFiledata(), headers);
//    }

    @PostMapping()
    public String fileCreate(@RequestParam("fileUpload") MultipartFile multipartFile, Model model, RedirectAttributes redirectAttributes){

        if(multipartFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("messageInformation", "Please, select a file before");
            return "redirect:/home";
        } else if (fileService.verifyFileExists(multipartFile.getOriginalFilename())){
            redirectAttributes.addFlashAttribute("messageInformation", "File already exists");
            return "redirect:/home";
        }

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
