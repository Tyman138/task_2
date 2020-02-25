package io.github.tyman138.task2.controller;

import io.github.tyman138.task2.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileController {
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/cars/upload")
    public void handleFileUpload(@RequestParam("file") MultipartFile file){
        fileService.save(file);
    }
}
