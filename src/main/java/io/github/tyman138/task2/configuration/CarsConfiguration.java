package io.github.tyman138.task2.configuration;

import io.github.tyman138.task2.controller.CarsController;
import io.github.tyman138.task2.repository.CarRepository;
import io.github.tyman138.task2.repository.FileRepository;
import io.github.tyman138.task2.service.CarService;
import io.github.tyman138.task2.service.impl.CarServiceImpl;
import io.github.tyman138.task2.validator.CarValidator;
import io.github.tyman138.task2.validator.DataBaseFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarsConfiguration {
    @Autowired
    private DataBaseFileValidator dataBaseFileValidator;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private CarValidator carValidator;

    @Bean
    public CarService carService() {
        return new CarServiceImpl(carRepository, fileRepository);
    }

    @Bean
    public CarsController carsController() {
        return new CarsController(carService, dataBaseFileValidator, carValidator);
    }

    @Bean
    public CarValidator carValidator() {
        return new CarValidator(carService);
    }
}