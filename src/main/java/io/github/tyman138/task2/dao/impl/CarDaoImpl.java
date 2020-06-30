package io.github.tyman138.task2.dao.impl;

import io.github.tyman138.task2.dao.CarDao;
import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.mapper.CarMapper;
import io.github.tyman138.task2.wrapper.NamedParameterJdbcTemplateWrapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.util.List;

public class CarDaoImpl implements CarDao {
    private NamedParameterJdbcTemplateWrapper namedParameterJdbcTemplateWrapper;

    public CarDaoImpl(NamedParameterJdbcTemplateWrapper namedParameterJdbcTemplateWrapper) {
        this.namedParameterJdbcTemplateWrapper = namedParameterJdbcTemplateWrapper;
    }

    @Override
    public List<Car> findAll() {
        String SQL = "" +
                "SELECT" +
                "   * " +
                "FROM " +
                "   CARS ";
        return namedParameterJdbcTemplateWrapper.query(SQL, new CarMapper());
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
        return namedParameterJdbcTemplateWrapper.queryForObject(SQL, namedParameters, new CarMapper());
    }

    @Override
    public Car save(Car car) {
        String SQL;
        if (car.getId() == 0) {
            long id = namedParameterJdbcTemplateWrapper.getJdbcOperations().queryForObject(
                    "SELECT " +
                            "NEXTVAL('hibernate_sequence')",
                    (resultSet, i) -> resultSet.getLong("nextval"));
            car.setId(id);
            SQL = "" +
                    "INSERT " +
                    "INTO " +
                    "   CARS " +
                    "VALUES " +
                    "   (:id, :mark, :year, :color, :country_factory)";
        } else {
            SQL = "" +
                    "Update " +
                    "   CARS " +
                    "SET " +
                    "   mark = :mark," +
                    "   year = :year," +
                    "   color = :color," +
                    "   country_factory = :country_factory " +
                    "WHERE " +
                    "   ID = :id";
        }
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", car.getId())
                .addValue("mark", car.getMark())
                .addValue("year", car.getYear())
                .addValue("color", car.getColor())
                .addValue("country_factory", car.getCountryFactory());
        namedParameterJdbcTemplateWrapper.update(SQL, namedParameters);
        return car;
    }

    @Override
    public void deleteById(long carId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", carId);
        String SQL = "" +
                "DELETE " +
                "FROM " +
                "   CARS " +
                "WHERE " +
                "   ID = :id";
        namedParameterJdbcTemplateWrapper.update(SQL, namedParameters);

    }

    @Override
    public boolean existsById(long carId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", carId);
        String SQL = "" +
                "SELECT " +
                "   1 " +
                "FROM " +
                "   CARS " +
                "WHERE " +
                "   ID = :id";
        return namedParameterJdbcTemplateWrapper.query(SQL, namedParameters, ResultSet::next);
    }
}
