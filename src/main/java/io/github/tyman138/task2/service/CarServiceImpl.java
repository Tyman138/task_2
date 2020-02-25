package io.github.tyman138.task2.service;

import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.entity.DataBaseInfoFile;
import io.github.tyman138.task2.fileReader.CustomFileReader;
import io.github.tyman138.task2.parser.Parser;
import io.github.tyman138.task2.repository.CarRepository;
import io.github.tyman138.task2.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private FileRepository fileRepository;

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
}
