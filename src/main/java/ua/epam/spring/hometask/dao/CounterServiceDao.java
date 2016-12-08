package ua.epam.spring.hometask.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import ua.epam.spring.hometask.dataManipulation.CounterDao;
import ua.epam.spring.hometask.domain.Counter;
import ua.epam.spring.hometask.domain.CounterType;
import ua.epam.spring.hometask.service.CounterService;
@Component
public class CounterServiceDao implements CounterService {

    @Autowired
    private CounterDao counterDao;

    @Override
    public void save(Counter counter) {
        counterDao.save(counter);
    }

    @Override
    public void delete(Counter counter) {
        counterDao.delete(counter);
    }

    @Override
    public Counter getById(Integer id) {
        return counterDao.getById(id);
    }

    @Override
    public List<Counter> getAll() {
        return counterDao.getAll();
    }

    @Override
    public Counter getByTypeAndKeyName(CounterType counterType, String keyName) {
        Optional<Counter> counter = getAll()
                .stream()
                .filter(c -> ((c.getCounterType() == counterType) &&
                        (c.getKeyName().equals(keyName))))
                .findFirst();

        return counter.get();
    }

    @Override
    public void update(Counter counter) {
        Preconditions.checkNotNull(counter.getId(), "Counter id should not be null");
        counterDao.update(counter);
    }
}
