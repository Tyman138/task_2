package io.github.tyman138.task2.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void save(MultipartFile file);

    boolean existById(long fileId);
}
