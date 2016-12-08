package ua.epam.spring.hometask.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.epam.spring.hometask.dataManipulation.EventDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;

@Component
public class EventServiceDao implements EventService {

	@Autowired
	private EventDao eventDao;

	@Override
	public Event save(Event object) {
		eventDao.save(object);
		return object;
	}

	@Override
	public void remove(Event object) {
		eventDao.delete(object);
	}

	@Override
	public Event getById(Integer id) {
		return eventDao.getById(id);
	}

	@Override
	public Event getByName(String name) {
		return eventDao.getByName(name);
	}

	@Override
	public void addAll(List<Event> events) {
		for (Event e : events) {
			save(e);
		}
	}

	@SuppressWarnings("null")
	@Override
	public List<String> getEventsName() {
		List<String> eventsName = null;
		for (Event e : getAllEvents()) {
			eventsName.add(e.getName());
		}
		return eventsName;
	}

	@Override
	public List<Event> getAllEvents() {
		return eventDao.getAll();
	}

}
