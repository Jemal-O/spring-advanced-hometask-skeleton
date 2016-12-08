package ua.epam.spring.hometask.dataManipulation;

import java.util.List;

import ua.epam.spring.hometask.domain.Event;

public interface EventDao {

	public void save(Event event);

	public void delete(Event event);

	public Event getById(Integer id);

	public List<Event> getAll();

	public void update(Event event);

	public Event getByName(String name);

}
