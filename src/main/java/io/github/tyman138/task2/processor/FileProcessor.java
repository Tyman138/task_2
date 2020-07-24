package io.github.tyman138.task2.processor;

import io.github.tyman138.task2.entity.DataBaseInfoFile;
import org.springframework.batch.item.ItemProcessor;


public class FileProcessor implements ItemProcessor<DataBaseInfoFile, DataBaseInfoFile> {
    private static final String STATUS = "PROCESSED";
    @Override
    public DataBaseInfoFile process(DataBaseInfoFile dataBaseInfoFile) throws Exception {
        DataBaseInfoFile processedFile = new DataBaseInfoFile();
        processedFile.setId(dataBaseInfoFile.getId());
        processedFile.setPath(dataBaseInfoFile.getPath());
        processedFile.setName(dataBaseInfoFile.getName());
        processedFile.setStatus(STATUS);
        return processedFile;
    }
}
