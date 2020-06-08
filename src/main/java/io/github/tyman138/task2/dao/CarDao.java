package io.github.tyman138.task2.dao;

import io.github.tyman138.task2.entity.Car;

import java.util.List;

public interface CarDao {
    List<Car> findAll();
    Car findById(long carId);
    void save(Car car);
    void saveCarsFromFile(long fileId);
    Car updateCarColorOnly(long carId, Car carColorOnly);
    void delete(long carId);
    boolean existById(long carId);
}
