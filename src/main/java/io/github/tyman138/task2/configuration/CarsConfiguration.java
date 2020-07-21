package io.github.tyman138.task2.configuration;

import io.github.tyman138.task2.controller.CarsController;
import io.github.tyman138.task2.dao.CarDao;
import io.github.tyman138.task2.dao.FileDao;
import io.github.tyman138.task2.repository.CarRepository;
import io.github.tyman138.task2.repository.FileRepository;
import io.github.tyman138.task2.service.CarService;
import io.github.tyman138.task2.service.FileService;
import io.github.tyman138.task2.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarsConfiguration {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CarDao carDao;

    @Autowired
    private FileDao fileDao;

    @Bean
    public CarService carService() {
        return new CarServiceImpl(carDao, fileDao);
    }

    @Bean
    public CarsController carsController() {
        return new CarsController(carService, fileService);
    }

}