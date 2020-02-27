package io.github.tyman138.task2.controller;

import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.service.CarService;
import io.github.tyman138.task2.validator.CarValidator;
import io.github.tyman138.task2.validator.DataBaseFileValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarsController {

    private CarService carService;
    private DataBaseFileValidator dataBaseFileValidator;
    private CarValidator carValidator;

    public CarsController(CarService carService, DataBaseFileValidator dataBaseFileValidator, CarValidator carValidator) {
        this.carService = carService;
        this.dataBaseFileValidator = dataBaseFileValidator;
        this.carValidator = carValidator;
    }

    @InitBinder({"fileId"})
    private void setDataBaseFileValidator(WebDataBinder binder) {
        binder.setValidator(dataBaseFileValidator);
        binder.setBindEmptyMultipartFiles(false);
    }

    @InitBinder({"carId"})
    private void setCarValidator(WebDataBinder binder) {
        binder.setValidator(carValidator);
    }

    @GetMapping
    public List<Car> findAllCars() {
        return carService.findAll();
    }

    @GetMapping("{carId}")
    public Car findCarById(@Valid @ModelAttribute(value = "carId") long carId) {
        return carService.findById(carId);
    }

    @PostMapping
    public void saveCar(@Valid @RequestBody Car car) {
        carService.save(car);
    }

    @PutMapping()
    public void changeCar(@Valid @RequestBody Car car) {
        carService.save(car);
    }

    @PatchMapping("{carId}")
    public Car changeCarColor(@Valid @ModelAttribute(value = "carId") long carId, @RequestBody Car carColorOnly) {
        return carService.updateCarColorOnly(carId, carColorOnly);
    }

    @DeleteMapping("{carId}")
    public void deleteCar(@Valid @ModelAttribute(value = "carId") long carId) {
        carService.delete(carId);
    }

    @PostMapping("/job/{fileId}")
    public void saveCarsFromFile(@Valid @ModelAttribute(value = "fileId") long fileId) {
        carService.saveCarsFromFile(fileId);
    }
}
