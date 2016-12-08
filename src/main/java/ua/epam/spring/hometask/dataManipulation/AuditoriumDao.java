package ua.epam.spring.hometask.dataManipulation;

import java.util.List;

import ua.epam.spring.hometask.domain.Auditorium;

public interface AuditoriumDao {

	public void save(Auditorium auditorium);

	public void delete(Auditorium auditorium);

	public Auditorium getById(Integer id);

	public List<Auditorium> getAll();

	public void update(Auditorium auditorium);

	public Auditorium getByName(String name);
	
	public List<Auditorium> getAllAssigning();
}
