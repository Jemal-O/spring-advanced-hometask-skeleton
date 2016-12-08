package ua.epam.spring.hometask.service;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.Event;

/**
 * @author Yuriy_Tkach
 */
public interface EventService extends AbstractDomainObjectService<Event> {

	/**
	 * Finding event by name
	 * 
	 * @param name
	 *            Name of the event
	 * @return found event or <code>null</code>
	 */
	public @Nullable Event getByName(@Nonnull String name);

	public Event getById(Integer id);

	public void addAll(List<Event> events);

	public List<String> getEventsName();
	public List<Event>  getAllEvents();

	/*
	 * Finding all events that air on specified date range
	 * 
	 * @param from Start date
	 * 
	 * @param to End date inclusive
	 * 
	 * @return Set of events
	 */
	// public @Nonnull Set<Event> getForDateRange(@Nonnull LocalDate from,
	// @Nonnull LocalDate to);

	/*
	 * Return events from 'now' till the the specified date time
	 * 
	 * @param to End date time inclusive s
	 * 
	 * @return Set of events
	 */
	// public @Nonnull Set<Event> getNextEvents(@Nonnull LocalDateTime to);

}