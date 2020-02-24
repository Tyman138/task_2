package io.github.tyman138.task2.parser;

import io.github.tyman138.task2.entity.Car;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<Car> ParseList(List<String> strings) {
        List<Car> cars = new ArrayList<>();
        strings.forEach(s ->
                cars.add(new Car(
                        s.split(",")[0],
                        Integer.valueOf(s.split(",")[1]),
                        s.split(",")[2],
                        s.split(",")[3]
                ))
        );
        return cars;
    }
}
