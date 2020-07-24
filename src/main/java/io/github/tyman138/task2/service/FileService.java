package io.github.tyman138.task2.service;

import io.github.tyman138.task2.entity.DataBaseInfoFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void save(MultipartFile file);

    void save(DataBaseInfoFile dataBaseInfoFile);

    void saveAll(List<MultipartFile> files);

    void saveAllDataBaseInfoFile(List<DataBaseInfoFile> files);

    boolean existById(long fileId);

    DataBaseInfoFile findById(long fileId);

    List<DataBaseInfoFile> findAllByStatusLoaded();

}
