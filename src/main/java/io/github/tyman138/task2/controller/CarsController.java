package io.github.tyman138.task2.controller;

import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarsController {
    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> findAllCars() {
        return carService.findAll();
    }

    @GetMapping("{id}")
    public Car findCarById(@PathVariable(value = "id") Long id) {
        return carService.findById(id);
    }

    @PostMapping
    public void saveCar(@Valid @RequestBody Car car) {
        carService.save(car);
    }

    @PutMapping()
    public void changeCar(@Valid @RequestBody Car car) {
        carService.save(car);
    }

    @PatchMapping("{id}")
    public Car changeCarColor(@PathVariable(value = "id") Long id, @RequestBody Car carColorOnly) {
        return carService.updateCarColorOnly(id, carColorOnly);
    }

    @DeleteMapping("{id}")
    public void deleteCar(@PathVariable(value = "id") Long id) {
        carService.delete(id);
    }

    @PostMapping("/job/{fileId}")
    public void saveCarsFromFile(@PathVariable(value = "fileId") Long fileId) {
        carService.saveCarsFromFile(fileId);
    }
}
