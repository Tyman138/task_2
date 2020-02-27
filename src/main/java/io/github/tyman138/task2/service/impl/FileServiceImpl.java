package io.github.tyman138.task2.service.impl;

import io.github.tyman138.task2.entity.DataBaseInfoFile;
import io.github.tyman138.task2.fileWriter.CustomFileWriter;
import io.github.tyman138.task2.repository.FileRepository;
import io.github.tyman138.task2.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;
    @Value("${db-file.path}")
    private String path;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void save(MultipartFile file) {
        new CustomFileWriter().writeToFile(file, path);
        fileRepository.save(new DataBaseInfoFile(path, file.getOriginalFilename()));
    }

    @Override
    public boolean existById(long fileId) {
        return fileRepository.existsById(fileId);
    }
}
