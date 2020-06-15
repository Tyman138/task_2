package io.github.tyman138.task2.dao.impl;

import io.github.tyman138.task2.dao.CarDao;
import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.entity.DataBaseInfoFile;
import io.github.tyman138.task2.fileReader.CustomFileReader;
import io.github.tyman138.task2.mapper.CarMapper;
import io.github.tyman138.task2.mapper.FileMapper;
import io.github.tyman138.task2.parser.Parser;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class CarDaoImpl implements CarDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CarDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Car> findAll() {
        String SQL = "" +
                "SELECT" +
                "   * " +
                "FROM " +
                "   CARS ";
        return namedParameterJdbcTemplate.query(SQL, new CarMapper());
    }

    @Override
    public Car findById(long carId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", carId);
        String SQL = "" +
                "SELECT " +
                "  * " +
                "FROM " +
                "  CARS " +
                "WHERE " +
                "  ID = :id";
        return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new CarMapper());
    }

    @Override
    public void save(Car car) {
        if (car.getId() == 0) {
            long id = namedParameterJdbcTemplate.getJdbcOperations().queryForObject(
                    "SELECT " +
                            "NEXTVAL('hibernate_sequence')",
                    (resultSet, i) -> resultSet.getLong("nextval"));
            String SQL = "" +
                    "INSERT " +
                    "INTO " +
                    "   CARS " +
                    "VALUES " +
                    "   (:id, :mark, :year, :color, :country_factory)";
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("id", id)
                    .addValue("mark", car.getMark())
                    .addValue("year", car.getYear())
                    .addValue("color", car.getColor())
                    .addValue("country_factory", car.getCountryFactory());
            namedParameterJdbcTemplate.update(SQL, namedParameters);
        } else {
            String SQL = "" +
                    "Update " +
                    "   CARS " +
                    "SET " +
                    "   mark = :mark," +
                    "   year = :year," +
                    "   color = :color," +
                    "   country_factory = :country_factory " +
                    "WHERE " +
                    "   ID = :id";
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("id", car.getId())
                    .addValue("mark", car.getMark())
                    .addValue("year", car.getYear())
                    .addValue("color", car.getColor())
                    .addValue("country_factory", car.getCountryFactory());
            namedParameterJdbcTemplate.update(SQL, namedParameters);
        }
    }

    @Override
    public void saveCarsFromFile(long fileId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", fileId);
        String SQL = "" +
                "SELECT " +
                "   * " +
                "FROM " +
                "   FILES " +
                "WHERE " +
                "   ID = :id";
        DataBaseInfoFile dataBaseInfoFile = namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new FileMapper());
        new Parser().ParseList(
                new CustomFileReader()
                        .readFileFromLocal(dataBaseInfoFile.getPath() + dataBaseInfoFile.getName())
        ).forEach(this::save);
    }

    @Override
    public Car updateCarColorOnly(long carId, Car carColorOnly) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", carId);
        String SQL = "" +
                "SELECT " +
                "   * " +
                "FROM " +
                "   CARS " +
                "WHERE " +
                "   ID = :id";
        Car car = namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new CarMapper());
        car.setColor(carColorOnly.getColor());
        this.save(car);
        return car;
    }

    @Override
    public void delete(long carId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", carId);
        String SQL = "" +
                "DELETE " +
                "FROM " +
                "   CARS " +
                "WHERE " +
                "   ID = :id";
        namedParameterJdbcTemplate.update(SQL, namedParameters);

    }

    @Override
    public boolean existById(long carId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", carId);
        String SQL = "" +
                "SELECT " +
                "   1 " +
                "FROM " +
                "   CARS " +
                "WHERE " +
                "   ID = :id";
        return namedParameterJdbcTemplate.query(SQL, namedParameters, ResultSet::next);
    }
}
