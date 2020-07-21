package io.github.tyman138.task2.configuration;

import io.github.tyman138.task2.controller.FileController;
import io.github.tyman138.task2.dao.FileDao;
import io.github.tyman138.task2.repository.FileRepository;
import io.github.tyman138.task2.service.FileService;
import io.github.tyman138.task2.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfiguration {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private FileRepository fileRepository;

    @Bean
    public FileServiceImpl fileService() {
        return new FileServiceImpl(fileDao);
    }

    @Bean
    public FileController fileController() {
        return new FileController(fileService);
    }

}
