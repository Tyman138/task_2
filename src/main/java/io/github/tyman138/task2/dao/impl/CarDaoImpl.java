package io.github.tyman138.task2.dao.impl;

import io.github.tyman138.task2.dao.CarDao;
import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.entity.DataBaseInfoFile;
import io.github.tyman138.task2.fileReader.CustomFileReader;
import io.github.tyman138.task2.mapper.CarMapper;
import io.github.tyman138.task2.mapper.FileMapper;
import io.github.tyman138.task2.parser.Parser;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class CarDaoImpl implements CarDao {
    private JdbcTemplate jdbcTemplate;

    public CarDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Car> findAll() {
        String SQL = "SELECT * FROM CARS";
        return jdbcTemplate.query(SQL, new CarMapper());
    }

    @Override
    public Car findById(long carId) {
        String SQL = "SELECT * FROM CARS WHERE ID = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{carId}, new CarMapper());
    }

    @Override
    public void save(Car car) {
        if (car.getId() == 0) {
            long id = jdbcTemplate.queryForObject("SELECT NEXTVAL('hibernate_sequence')", (resultSet, i) -> resultSet.getLong("nextval"));
            String SQL = "INSERT INTO CARS (id, mark, year, color, country_factory) VALUES (?,?,?,?,?)";
            jdbcTemplate.update(SQL, id, car.getMark(), car.getYear(), car.getColor(), car.getCountryFactory());
        } else {
            String SQL = "Update CARS SET mark = ?, year = ?, color = ?, country_factory = ? WHERE ID = ?";
            jdbcTemplate.update(SQL, car.getMark(), car.getYear(), car.getColor(), car.getCountryFactory(), car.getId());
        }
    }

    @Override
    public void saveCarsFromFile(long fileId) {
        String SQL = "SELECT * FROM FILES WHERE ID = ?";
        DataBaseInfoFile dataBaseInfoFile = jdbcTemplate.queryForObject(SQL, new Object[]{fileId}, new FileMapper());
        new Parser().ParseList(
                new CustomFileReader()
                        .readFileFromLocal(dataBaseInfoFile.getPath() + dataBaseInfoFile.getName())
        ).forEach(this::save);
    }

    @Override
    public Car updateCarColorOnly(long carId, Car carColorOnly) {
        String SQL = "SELECT * FROM CARS WHERE ID = ?";
        Car car = jdbcTemplate.queryForObject(SQL, new Object[]{carId}, new CarMapper());
        car.setColor(carColorOnly.getColor());
        this.save(car);
        return car;
    }

    @Override
    public void delete(long carId) {
        String SQL = "DELETE FROM CARS WHERE ID = ?";
        jdbcTemplate.update(SQL, carId);

    }

    @Override
    public boolean existById(long carId) {
        String SQL = "Select 1 from CARS Where ID = ?";
        return jdbcTemplate.query(SQL, new Object[]{carId}, ResultSet::next);
    }
}
