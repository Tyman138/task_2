package io.github.tyman138.task2.service;

import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.filter.CarFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CarService {
    List<Car> findAll(CarFilter carFilter, Pageable pageable);

    Car findById(long carId);

    void save(Car car);

    void saveAll(List<Car> carList);

    void saveCarsFromFile(long fileId);

    Car updateCarColorOnly(long carId, Car carColorOnly);

    void delete(long carId);

    boolean existById(long carId);
}
