package io.github.tyman138.task2.service.impl;

import io.github.tyman138.task2.dao.CarDao;
import io.github.tyman138.task2.dao.FileDao;
import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.entity.DataBaseInfoFile;
import io.github.tyman138.task2.fileReader.CustomFileReader;
import io.github.tyman138.task2.filter.CarFilter;
import io.github.tyman138.task2.parser.Parser;
import io.github.tyman138.task2.service.CarService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarDao carDao;
    private FileDao fileDao;

    public CarServiceImpl(CarDao carDao, FileDao fileDao) {
        this.carDao = carDao;
        this.fileDao = fileDao;
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
        DataBaseInfoFile dataBaseInfoFile = fileDao.findById(fileId);
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
