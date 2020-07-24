package io.github.tyman138.task2.dao;

import io.github.tyman138.task2.entity.DataBaseInfoFile;

import java.util.List;

public interface FileDao {
    DataBaseInfoFile findById(long fileId);
    List<DataBaseInfoFile> findAll();
    DataBaseInfoFile save(DataBaseInfoFile file);
    void saveAll(List<DataBaseInfoFile> files);
    boolean existsById(long fileId);
    List<DataBaseInfoFile> findAllByStatusLoaded();
}
