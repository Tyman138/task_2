package io.github.tyman138.task2.controller;

import io.github.tyman138.task2.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileController {
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/cars/upload")
    public void handleFileUpload(@RequestParam("file") MultipartFile file) {
        fileService.save(file);
    }

    @PostMapping("/cars/upload/files")
    public void handleFileUpload(@RequestParam("file") List<MultipartFile> files) {
        fileService.saveAll(files);
    }

}
