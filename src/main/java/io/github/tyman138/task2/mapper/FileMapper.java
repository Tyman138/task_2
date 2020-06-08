package io.github.tyman138.task2.mapper;

import io.github.tyman138.task2.entity.DataBaseInfoFile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileMapper implements RowMapper<DataBaseInfoFile> {
    @Override
    public DataBaseInfoFile mapRow(ResultSet resultSet, int i) throws SQLException {
        DataBaseInfoFile dataBaseInfoFile = new DataBaseInfoFile();
        dataBaseInfoFile.setId(resultSet.getLong("id"));
        dataBaseInfoFile.setPath(resultSet.getString("path"));
        dataBaseInfoFile.setName(resultSet.getString("name"));
        return dataBaseInfoFile;
    }
}
