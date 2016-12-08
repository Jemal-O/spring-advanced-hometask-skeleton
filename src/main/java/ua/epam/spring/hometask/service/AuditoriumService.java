package ua.epam.spring.hometask.service;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.Auditorium;

/**
 * @author Yuriy_Tkach
 */
public interface AuditoriumService {

	/**
	 * Getting all auditoriums from the system
	 * 
	 * @return set of all auditoriums
	 */
	public @Nonnull List<Auditorium> getAll();

	/**
	 * Finding auditorium by name
	 * 
	 * @param name
	 *            Name of the auditorium
	 * @return found auditorium or <code>null</code>
	 */
	public @Nullable Auditorium getByName(@Nonnull String name);

	public Auditorium add(Auditorium auditoriumAddition);

	public List<Auditorium> getAssignedAuditoriums();

	public void update(Auditorium auditorium);

	public Auditorium getById(Integer id);

	public void addAll(List<Auditorium> auditoriums);

}
