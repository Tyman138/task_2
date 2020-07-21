package io.github.tyman138.task2.dao.impl;

import io.github.tyman138.task2.dao.FileDao;
import io.github.tyman138.task2.entity.DataBaseInfoFile;
import io.github.tyman138.task2.mapper.FileMapper;
import io.github.tyman138.task2.wrapper.NamedParameterJdbcTemplateWrapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.util.List;

public class FileDaoImpl implements FileDao {
    private NamedParameterJdbcTemplateWrapper namedParameterJdbcTemplateWrapper;

    public FileDaoImpl(NamedParameterJdbcTemplateWrapper namedParameterJdbcTemplateWrapper) {
        this.namedParameterJdbcTemplateWrapper = namedParameterJdbcTemplateWrapper;
    }

    @Override
    public DataBaseInfoFile findById(long fileId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", fileId);
        String SQL = "" +
                "SELECT " +
                "  * " +
                "FROM " +
                "  FILES " +
                "WHERE " +
                "  ID = :id";
        return namedParameterJdbcTemplateWrapper.queryForObject(SQL, namedParameters, new FileMapper());
    }

    @Override
    public List<DataBaseInfoFile> findAll() {
        String SQL = "" +
                "SELECT" +
                "   * " +
                "FROM " +
                "   FILES ";
        return namedParameterJdbcTemplateWrapper.query(SQL, new FileMapper());
    }

    @Override
    public DataBaseInfoFile save(DataBaseInfoFile file) {
        String SQL;
        if (file.getId() == 0) {
            long id = namedParameterJdbcTemplateWrapper.getJdbcOperations().queryForObject(
                    "SELECT " +
                            "NEXTVAL('hibernate_sequence')",
                    (resultSet, i) -> resultSet.getLong("nextval"));
            file.setId(id);
            SQL = "" +
                    "INSERT " +
                    "INTO " +
                    "   FILES " +
                    "VALUES " +
                    "   (:id, :path, :name, :status)";
        } else {
            SQL = "" +
                    "Update " +
                    "   FILES " +
                    "SET " +
                    "   path = :path," +
                    "   name = :name," +
                    "   status = :status," +
                    "WHERE " +
                    "   ID = :id";
        }
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", file.getId())
                .addValue("path", file.getPath())
                .addValue("name", file.getName())
                .addValue("status", file.getStatus());
        namedParameterJdbcTemplateWrapper.update(SQL, namedParameters);
        return file;
    }

    @Override
    public void saveAll(List<DataBaseInfoFile> files) {
        files.forEach(this::save);
    }

    @Override
    public boolean existsById(long fileId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", fileId);
        String SQL = "" +
                "SELECT " +
                "   1 " +
                "FROM " +
                "   FILES " +
                "WHERE " +
                "   ID = :id";
        return namedParameterJdbcTemplateWrapper.query(SQL, namedParameters, ResultSet::next);
    }
}
