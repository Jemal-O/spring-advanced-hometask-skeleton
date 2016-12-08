package ua.epam.spring.hometask.dataManipulation;

import java.util.List;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

public interface TicketDao {

	public void save(Ticket ticket);

	public void delete(Ticket ticket);

	public Ticket getById(Integer id);

	public List<Ticket> getAll();

	public void update(Ticket ticket);

	public double getTicketPrice(Event event, User user, Integer seat);

	public List<Ticket> getPurchasedForEvent(Event event);

	public List<Ticket> getAllByUser(User user);
}
