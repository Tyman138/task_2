package io.github.tyman138.task2.configuration;

import io.github.tyman138.task2.dao.CarDao;
import io.github.tyman138.task2.dao.impl.CarDaoImpl;
import io.github.tyman138.task2.wrapper.NamedParameterJdbcTemplateWrapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringJdbcConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public NamedParameterJdbcTemplateWrapper namedParameterJdbcTemplateWrapper() {
        return new NamedParameterJdbcTemplateWrapper(dataSource());
    }

    @Bean
    public CarDao carDao() {
        return new CarDaoImpl(namedParameterJdbcTemplateWrapper());
    }
}
