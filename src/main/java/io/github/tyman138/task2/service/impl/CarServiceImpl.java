package io.github.tyman138.task2.service.impl;

import io.github.tyman138.task2.dao.CarDao;
import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.entity.DataBaseInfoFile;
import io.github.tyman138.task2.fileReader.CustomFileReader;
import io.github.tyman138.task2.filter.CarFilter;
import io.github.tyman138.task2.parser.Parser;
import io.github.tyman138.task2.repository.FileRepository;
import io.github.tyman138.task2.service.CarService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarDao carDao;
    private FileRepository fileRepository;

    public CarServiceImpl(CarDao carDao, FileRepository fileRepository) {
        this.carDao = carDao;
        this.fileRepository = fileRepository;
    }

    @Override
    public List<Car> findAll(CarFilter carFilter, Pageable pageable) {
        return carDao.findAll();

    }

    @Override
    public Car findById(long carId) {
        return carDao.findById(carId);
    }

    @Override
    public void save(Car car) {
        carDao.save(car);
    }


    @Override
    public void saveCarsFromFile(long fileId) {
        DataBaseInfoFile dataBaseInfoFile = fileRepository.findById(fileId).orElseThrow(EntityNotFoundException::new);
        new Parser().ParseList(
                new CustomFileReader()
                        .readFileFromLocal(dataBaseInfoFile.getPath() + dataBaseInfoFile.getName())
        ).forEach(car -> carDao.save(car));
    }

    @Override
    public Car updateCarColorOnly(long carId, Car carColorOnly) {
        Car car = findById(carId);
        car.setColor(carColorOnly.getColor());
        return carDao.save(car);
    }

    @Override
    public void delete(long carId) {
        carDao.deleteById(carId);
    }

    @Override
    public boolean existById(long carId) {
        return carDao.existsById(carId);
    }
}
