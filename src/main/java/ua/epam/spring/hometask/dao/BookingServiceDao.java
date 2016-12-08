package ua.epam.spring.hometask.dao;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.epam.spring.hometask.dataManipulation.TicketDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.AccountsService;
import ua.epam.spring.hometask.service.BookingService;

@Service
@Transactional
public class BookingServiceDao implements BookingService {

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private AccountsService accountsService;

	@Override
	public double getTicketsPrice(Event event, User user, Integer seats) {
		return ticketDao.getTicketPrice(event, user, seats);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void bookTickets(User user, Ticket ticket) throws Exception {
		ticket.setUserId(user.getId());
		accountsService.checkAndUpdateMoney(user, ticket);
		ticketDao.save(ticket);
	}

	@Override
	public void checkLucky(Ticket ticket, User user) {
		boolean lucky = getRandomBoolean();
		if (lucky) {
			ticket.setTicketPrice(0);
		}
	}

	private boolean getRandomBoolean() {
		Random random = new Random();
		return random.nextBoolean();
	}

	@Override
	public List<Ticket> getPurchasedTicketsForEvent(Event event) {
		return ticketDao.getPurchasedForEvent(event);
	}

	@Override
	public List<Ticket> getAllTickets() {
		return ticketDao.getAll();
	}

	@Override
	public void addAll(List<Ticket> tickets) {
		for (Ticket t : tickets) {
			save(t);
		}

	}

	@Override
	public void save(Ticket ticket) {
		ticketDao.save(ticket);

	}
}
