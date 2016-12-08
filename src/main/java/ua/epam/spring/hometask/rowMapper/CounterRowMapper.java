package ua.epam.spring.hometask.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.epam.spring.hometask.domain.Counter;
import ua.epam.spring.hometask.domain.CounterType;

public class CounterRowMapper implements RowMapper<Counter> {

    @Override
    public Counter mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Counter(rs.getInt("id"),
                CounterType.valueOf(rs.getString("counterType")),
                rs.getString("keyName"),
                rs.getInt("valueCounter"));
    }

}
