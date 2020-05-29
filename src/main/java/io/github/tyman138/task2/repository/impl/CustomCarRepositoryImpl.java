package io.github.tyman138.task2.repository.impl;

import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.filter.CarFilter;
import io.github.tyman138.task2.repository.CustomCarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CustomCarRepositoryImpl implements CustomCarRepository {


    @Override
    public Page<Car> findAll(CarFilter filter, Pageable pageable) {

        //CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        return null;
    }
}
