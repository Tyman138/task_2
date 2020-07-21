package io.github.tyman138.task2.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void save(MultipartFile file);

    void saveAll(List<MultipartFile> files);

    boolean existById(long fileId);
}
