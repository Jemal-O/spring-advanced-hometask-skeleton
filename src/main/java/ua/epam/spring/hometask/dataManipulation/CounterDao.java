package ua.epam.spring.hometask.dataManipulation;

import java.util.List;

import ua.epam.spring.hometask.domain.Counter;


public interface CounterDao {

    public void save(Counter counter);

    public void delete(Counter counter);

    public Counter getById(Integer id);

    public List<Counter> getAll();

    public void update(Counter counter);
}
