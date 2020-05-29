package io.github.tyman138.task2.controller;

import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.filter.CarFilter;
import io.github.tyman138.task2.service.CarService;
import io.github.tyman138.task2.service.FileService;
import io.github.tyman138.task2.validator.CarValidator;
import io.github.tyman138.task2.validator.DataBaseFileValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarsController {

    private CarService carService;

    private FileService fileService;


    public CarsController(CarService carService, FileService fileService) {
        this.carService = carService;
        this.fileService = fileService;
    }

    @InitBinder({"fileId"})
    private void setDataBaseFileValidator(WebDataBinder binder) {
        binder.setValidator(new DataBaseFileValidator(fileService));
        binder.setBindEmptyMultipartFiles(false);
    }

    @InitBinder({"carId"})
    private void setCarValidator(WebDataBinder binder) {
        binder.setValidator(new CarValidator(carService));
    }

    @GetMapping
    public Page<Car> findAllCars(CarFilter carFilter, Pageable pageable) {
        return carService.findAll(carFilter, pageable);
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
