package io.github.tyman138.task2.configuration;

import io.github.tyman138.task2.controller.CarsController;
import io.github.tyman138.task2.dao.CarDao;
import io.github.tyman138.task2.dao.impl.CarDaoImpl;
import io.github.tyman138.task2.repository.CarRepository;
import io.github.tyman138.task2.repository.FileRepository;
import io.github.tyman138.task2.service.CarService;
import io.github.tyman138.task2.service.FileService;
import io.github.tyman138.task2.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
//@EnableJpaRepositories(basePackages = "io.github.tyman138.task2.repository",repositoryBaseClass = io.github.tyman138.task2.repository.impl.DefaultRepositoryImpl.class)
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

    @Bean
    public CarService carService() {
        return new CarServiceImpl(carRepository, fileRepository);
    }

    @Bean
    public CarsController carsController() {
        return new CarsController(carService, fileService,carDao);
    }

}