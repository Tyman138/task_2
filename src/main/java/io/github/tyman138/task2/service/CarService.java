package io.github.tyman138.task2.service;

import io.github.tyman138.task2.entity.Car;

import java.util.List;

public interface CarService { //fixme переделай названия
    List<Car> findAll();
    Car findById(Long id);
    void save(Car car);
    void saveCarsFromFile(Long fileId);
    Car updateCarColorOnly(Long id,Car carColorOnly);
    void delete(Long id);
}
