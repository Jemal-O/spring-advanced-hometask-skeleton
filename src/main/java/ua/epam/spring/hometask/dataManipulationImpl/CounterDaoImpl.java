package ua.epam.spring.hometask.dataManipulationImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.epam.spring.hometask.dataManipulation.CounterDao;
import ua.epam.spring.hometask.domain.Counter;
import ua.epam.spring.hometask.rowMapper.CounterRowMapper;

@Repository
public class CounterDaoImpl implements CounterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Counter counter) {
         jdbcTemplate.update("INSERT INTO counters (id, counterType, keyName, valueCounter) VALUES (?,?,?,?)",
                null,
                counter.getCounterType().name(),
                counter.getKeyName(),
                counter.getValueCounter());
    }

    @Override
    public void delete(Counter counter) {
        jdbcTemplate.update("DELETE FROM counters WHERE counters.id = ?",
                counter.getId());
    }

    @Override
    public Counter getById(Integer id) {
        return (Counter) jdbcTemplate.queryForObject("SELECT * FROM counters WHERE counters.id = ?",
                new Object[] {id},
                new CounterRowMapper());
    }

    @Override
    public List<Counter> getAll() {
        return jdbcTemplate.query("SELECT * FROM counters",
                new CounterRowMapper());
    }

    @Override
    public void update(Counter counter) {
        jdbcTemplate.update("UPDATE counters SET counterType = ?, keyName = ?, valueCounter = ? WHERE counters.id = ?",
                counter.getCounterType(),
                counter.getKeyName(),
                counter.getValueCounter(),
                counter.getId());
    }

}
