package io.github.tyman138.task2.service;

import io.github.tyman138.task2.entity.DataBaseInfoFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    DataBaseInfoFile findById(Long id);
    void save(MultipartFile file);
}
