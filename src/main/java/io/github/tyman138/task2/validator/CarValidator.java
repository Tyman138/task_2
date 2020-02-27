package io.github.tyman138.task2.validator;

import io.github.tyman138.task2.service.CarService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CarValidator implements Validator {
    private static final String ID = "id";

    private CarService carService;

    public CarValidator(CarService carService) {
        this.carService = carService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Long.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (!carService.existById((long) o)) {
            errors.reject(ID, "Car with entered Id doesn't exist");
        }
    }
}
