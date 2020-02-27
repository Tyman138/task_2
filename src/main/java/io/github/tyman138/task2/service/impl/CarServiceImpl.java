package io.github.tyman138.task2.service.impl;

import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.entity.DataBaseInfoFile;
import io.github.tyman138.task2.fileReader.CustomFileReader;
import io.github.tyman138.task2.parser.Parser;
import io.github.tyman138.task2.repository.CarRepository;
import io.github.tyman138.task2.repository.FileRepository;
import io.github.tyman138.task2.service.CarService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private FileRepository fileRepository;

    public CarServiceImpl(CarRepository carRepository, FileRepository fileRepository) {
        this.carRepository = carRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(long carId) {
        return carRepository.findById(carId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }


    @Override
    public void saveCarsFromFile(long fileId) {
        DataBaseInfoFile dataBaseInfoFile = fileRepository.findById(fileId).orElseThrow(EntityNotFoundException::new);
        carRepository.saveAll(
                new Parser().ParseList(
                        new CustomFileReader()
                                .readFileFromLocal(dataBaseInfoFile.getPath() + dataBaseInfoFile.getName())
                )
        );
    }

    @Override
    public Car updateCarColorOnly(long carId, Car carColorOnly) {
        Car car = findById(carId);
        car.setColor(carColorOnly.getColor());
        return carRepository.save(car);

    }

    @Override
    public void delete(long carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public boolean existById(long carId) {
        return carRepository.existsById(carId);
    }
}
