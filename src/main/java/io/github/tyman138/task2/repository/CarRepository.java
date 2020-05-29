package io.github.tyman138.task2.repository;

import io.github.tyman138.task2.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long>, CustomCarRepository/*, JpaSpecificationExecutor<Car>*/ {

}
