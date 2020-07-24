package io.github.tyman138.task2.wrapper;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.util.List;

public class NamedParameterJdbcTemplateWrapper {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NamedParameterJdbcTemplateWrapper(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        return namedParameterJdbcTemplate.query(sql, rowMapper);
    }

    public <T> List<T> query(String sql,SqlParameterSource paramSource, RowMapper<T> rowMapper) {
        return namedParameterJdbcTemplate.query(sql, paramSource, rowMapper);
    }

    public <T> T query(String sql, SqlParameterSource paramSource, ResultSetExtractor<T> rse) {
        return namedParameterJdbcTemplate.query(sql, paramSource, rse);
    }

    public <T> T queryForObject(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper) {
        return namedParameterJdbcTemplate.queryForObject(sql, paramSource, rowMapper);
    }

    public JdbcOperations getJdbcOperations() {
        return namedParameterJdbcTemplate.getJdbcOperations();
    }

    public int update(String sql, SqlParameterSource paramSource) {
        return namedParameterJdbcTemplate.update(sql, paramSource);
    }
}
