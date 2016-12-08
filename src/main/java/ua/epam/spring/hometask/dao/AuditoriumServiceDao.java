package ua.epam.spring.hometask.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.epam.spring.hometask.dataManipulation.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

@Component
public class AuditoriumServiceDao implements AuditoriumService {
	@Autowired
	private AuditoriumDao auditoriumDao;

	@Override
	public List<Auditorium> getAll() {
		return auditoriumDao.getAll();
	}

	@Override
	public Auditorium getByName(String name) {
		return auditoriumDao.getByName(name);
	}

	@Override
	public Auditorium add(Auditorium object) {
		auditoriumDao.save(object);
		return object;
	}

	@Override
	public List<Auditorium> getAssignedAuditoriums() {
		return auditoriumDao.getAllAssigning();
	}

	@Override
	public void update(Auditorium auditorium) {
		auditoriumDao.update(auditorium);
	}

	@Override
	public Auditorium getById(Integer id) {
		return auditoriumDao.getById(id);
	}

	@Override
	public void addAll(List<Auditorium> auditoriums) {
		for (Auditorium a : auditoriums) {
			add(a);
		}

	}
}
