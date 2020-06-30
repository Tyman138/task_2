package io.github.tyman138.task2.dao;

import io.github.tyman138.task2.entity.Car;

import java.util.List;

public interface CarDao {
    List<Car> findAll();
    Car findById(long carId);
    Car save(Car car);
    void deleteById(long carId);
    boolean existsById(long carId);
}
