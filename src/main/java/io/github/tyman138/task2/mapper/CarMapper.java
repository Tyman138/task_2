package io.github.tyman138.task2.mapper;

import io.github.tyman138.task2.entity.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet resultSet, int i) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong("id"));
        car.setMark(resultSet.getString("mark"));
        car.setYear(resultSet.getInt("year"));
        car.setColor(resultSet.getString("color"));
        car.setCountryFactory(resultSet.getString("country_factory"));
        return car;
    }
}
