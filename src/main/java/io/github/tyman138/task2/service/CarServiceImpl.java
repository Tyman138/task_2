package io.github.tyman138.task2.service;

import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.entity.DataBaseInfoFile;
import io.github.tyman138.task2.fileReader.CustomFileReader;
import io.github.tyman138.task2.parser.Parser;
import io.github.tyman138.task2.repository.CarRepository;
import io.github.tyman138.task2.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }


    @Override
    public void saveCarsFromFile(Long fileId) {
        DataBaseInfoFile dataBaseInfoFile = fileRepository.findById(fileId).orElseThrow(NullPointerException::new);
        carRepository.saveAll(
                new Parser().ParseList(
                        new CustomFileReader()
                                .readFileFromLocal(dataBaseInfoFile.getPath() + dataBaseInfoFile.getName())
                )
        );
    }

    @Override
    public Car updateCarColorOnly(Long id, Car carColorOnly) {
        Car car = findById(id);
        car.setColor(carColorOnly.getColor());
        return carRepository.save(car);

    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
