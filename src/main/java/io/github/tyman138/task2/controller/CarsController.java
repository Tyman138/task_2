package io.github.tyman138.task2.controller;

import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarsController {

    private CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> findAllCars() {
        return carService.findAll();
    }

    @GetMapping("{carId}")
    public Car findCarById(@PathVariable long carId) {
        return carService.findById(carId);
    }

    @PostMapping
    public void saveCar(@RequestBody Car car) {
        carService.save(car);
    }

    @PutMapping()
    public void changeCar(@RequestBody Car car) {
        carService.save(car);
    }

    @PatchMapping("{carId}")
    public Car changeCarColor(@PathVariable long carId, @RequestBody Car carColorOnly) {
        return carService.updateCarColorOnly(carId, carColorOnly);
    }

    @DeleteMapping("{carId}")
    public void deleteCar(@PathVariable long carId) {
        carService.delete(carId);
    }

    @PostMapping("/job/{fileId}")
    public void saveCarsFromFile(@PathVariable long fileId) {
        carService.saveCarsFromFile(fileId);
    }
}
