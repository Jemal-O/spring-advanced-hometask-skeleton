package ua.epam.spring.hometask.service;

import java.util.List;

import ua.epam.spring.hometask.domain.Counter;
import ua.epam.spring.hometask.domain.CounterType;

public interface CounterService {
    public void save(Counter counter);

    public void delete(Counter counter);

    public Counter getById(Integer id);

    public Counter getByTypeAndKeyName(CounterType counterType, String keyName);

    public List<Counter> getAll();

    public void update(Counter counter);

}
