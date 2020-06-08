package io.github.tyman138.task2.controller;

import io.github.tyman138.task2.dao.CarDao;
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
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarsController {

    private CarService carService;

    private FileService fileService;

    private CarDao carDao;


    public CarsController(CarService carService, FileService fileService, CarDao carDao) {
        this.carService = carService;
        this.fileService = fileService;
        this.carDao = carDao;
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

    @GetMapping("/jdbc")
    public List<Car> findCarsByJDBC() {
        return carDao.findAll();
    }
    @GetMapping("/jdbc/exist/{carId}")
    public boolean existByJDBC(@Valid @ModelAttribute(value = "carId") long carId) {
        return carDao.existById(carId);
    }

    @GetMapping("{carId}")
    public Car findCarById(@Valid @ModelAttribute(value = "carId") long carId) {
        return carService.findById(carId);
    }

    @GetMapping("/jdbc/{carId}")
    public Car findCarByIdByJdbc(@Valid @ModelAttribute(value = "carId") long carId) {
        return carDao.findById(carId);
    }

    @PostMapping
    public void saveCar(@Valid @RequestBody Car car) {
        carService.save(car);
    }

    @PostMapping("/jdbc")
    public void saveCarByJdbc(@Valid @RequestBody Car car) {
        carDao.save(car);
    }

    @PutMapping()
    public void changeCar(@Valid @RequestBody Car car) {
        carService.save(car);
    }

    @PutMapping("/jdbc")
    public void changeCarByJdbc(@Valid @RequestBody Car car) {
        carService.save(car);
    }

    @PatchMapping("{carId}")
    public Car changeCarColor(@Valid @ModelAttribute(value = "carId") long carId, @RequestBody Car carColorOnly) {
        return carService.updateCarColorOnly(carId, carColorOnly);
    }

    @PatchMapping("/jdbc/{carId}")
    public Car changeCarColorByJdbc(@Valid @ModelAttribute(value = "carId") long carId, @RequestBody Car carColorOnly) {
        return carDao.updateCarColorOnly(carId, carColorOnly);
    }

    @DeleteMapping("{carId}")
    public void deleteCar(@Valid @ModelAttribute(value = "carId") long carId) {
        carService.delete(carId);
    }

    @DeleteMapping("/jdbc/{carId}")
    public void deleteCarByJdbc(@Valid @ModelAttribute(value = "carId") long carId) {
        carDao.delete(carId);
    }

    @PostMapping("/job/{fileId}")
    public void saveCarsFromFile(@Valid @ModelAttribute(value = "fileId") long fileId) {
        carService.saveCarsFromFile(fileId);
    }

    @PostMapping("/job/jdbc/{fileId}")
    public void saveCarsFromFileByJdbc(@Valid @ModelAttribute(value = "fileId") long fileId) {
        carDao.saveCarsFromFile(fileId);
    }
}
