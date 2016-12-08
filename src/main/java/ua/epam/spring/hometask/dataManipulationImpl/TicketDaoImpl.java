package ua.epam.spring.hometask.dataManipulationImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.epam.spring.hometask.dataManipulation.TicketDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.rowMapper.TicketRowMapper;

@Repository
public class TicketDaoImpl implements TicketDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Ticket ticket) {
		jdbcTemplate.update(
				"INSERT INTO tickets (eventId, userId, seat, ticketPrice, isPurchased) VALUES (?,?,?,?,?)", 
				ticket.getEventId(), 
				ticket.getUserId(), 
				ticket.getSeat(), 
				ticket.getTicketPrice(),
				ticket.getIsPurchased());
	}

	@Override
	public void delete(Ticket ticket) {
		jdbcTemplate.update("DELETE FROM tickets WHERE tickets.id = ?", ticket.getId());
	}

	@Override
	public Ticket getById(Integer id) {
		return jdbcTemplate.queryForObject("SELECT * FROM tickets WHERE tickets.id = ?", new Object[] { id },
				new TicketRowMapper());
	}

	@Override
	public List<Ticket> getAll() {
		return jdbcTemplate.query("SELECT * FROM tickets", new TicketRowMapper());
	}

	@Override
	public void update(Ticket ticket) {
		jdbcTemplate.update(
				"UPDATE tickets SET eventId = ?, userId = ?, seat = ?, ticketPrice=? isPurchased = ? WHERE tickets.id = ?",
				ticket.getEventId(), ticket.getUserId(), ticket.getSeat(), ticket.getTicketPrice(),
				ticket.getIsPurchased(), ticket.getId());
	}

	@Override
	public double getTicketPrice(Event event, User user, Integer seat) {
		Ticket t = jdbcTemplate.queryForObject(
				"SELECT * FROM tickets WHERE tickets.eventId = ? AND tickets.userId = ? AND tickets.seat=?",
				new Object[] { event.getId(), user.getId(), seat }, new TicketRowMapper());
		return t.getTicketPrice();
	}

	@Override
	public List<Ticket> getPurchasedForEvent(Event event) {
		return jdbcTemplate.query("SELECT * FROM tickets WHERE tickets.eventId=? AND isPurchased=true",
				new Object[] { event.getId() }, new TicketRowMapper());
	}

	@Override
	public List<Ticket> getAllByUser(User user) {
		return jdbcTemplate.query("SELECT * FROM tickets WHERE tickets.userId=?", new Object[] { user.getId() },
				new TicketRowMapper());
	}

}
