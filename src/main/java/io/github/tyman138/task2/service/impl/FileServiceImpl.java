package io.github.tyman138.task2.service.impl;

import io.github.tyman138.task2.dao.FileDao;
import io.github.tyman138.task2.entity.DataBaseInfoFile;
import io.github.tyman138.task2.fileWriter.CustomFileWriter;
import io.github.tyman138.task2.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private static final String STATUS = "LOADED";

    private FileDao fileDao;
    @Value("${db-file.path}")
    private String path;

    public FileServiceImpl(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    @Override
    public void save(MultipartFile file) {
        new CustomFileWriter().writeToFile(file, path);
        fileDao.save(new DataBaseInfoFile(path, file.getOriginalFilename(), STATUS));
    }

    @Override
    public void saveAll(List<MultipartFile> files) {
        for (MultipartFile multipartFile : files) {
            this.save(multipartFile);
        }
    }

    @Override
    public boolean existById(long fileId) {
        return fileDao.existsById(fileId);
    }
}
