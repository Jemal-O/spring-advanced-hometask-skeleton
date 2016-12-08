package ua.epam.spring.hometask.service;

import java.util.List;

import javax.annotation.Nonnull;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

/**
 * @author Yuriy_Tkach
 */
public interface BookingService {

	/**
	 * Getting price when buying all supplied seats for particular event
	 * 
	 * @param event
	 *            Event to get base ticket price, vip seats and other
	 *            information
	 * @param dateTime
	 *            Date and time of event air
	 * @param user
	 *            User that buys ticket could be needed to calculate discount.
	 *            Can be <code>null</code>
	 * @param seats
	 *            Set of seat numbers that user wants to buy
	 * @return total price
	 */
	public void checkLucky(Ticket ticket, User user);

	public void bookTickets(User user, Ticket ticket) throws Exception;

	/**
	 * Books tickets in internal system. If user is not <code>null</code> in a
	 * ticket then booked tickets are saved with it
	 * 
	 * @param tickets
	 *            Set of tickets
	 */

	/**
	 * Getting all purchased tickets for event on specific air date and time
	 * 
	 * @param event
	 *            Event to get tickets for
	 * @param dateTime
	 *            Date and time of airing of event
	 * @return set of all purchased tickets
	 */
	public @Nonnull List<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event);

	public double getTicketsPrice(Event event, User user, Integer seats);

	public List<Ticket> getAllTickets();

	public void addAll(List<Ticket> tickets);

	public void save(Ticket ticket);

}
