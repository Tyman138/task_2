package io.github.tyman138.task2.repository;

import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.filter.CarFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomCarRepository {

    Page<Car> findAll(CarFilter filter, Pageable pageable);

}
