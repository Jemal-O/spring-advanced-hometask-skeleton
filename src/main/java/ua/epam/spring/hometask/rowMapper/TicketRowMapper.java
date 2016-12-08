package ua.epam.spring.hometask.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.epam.spring.hometask.domain.Ticket;

public class TicketRowMapper implements RowMapper<Ticket> {

	@Override
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ticket ticket = new Ticket();
		ticket.setId(rs.getInt("id"));
		ticket.setEventId(rs.getInt("eventId"));
		ticket.setUserId(rs.getInt("userId"));
		ticket.setSeat(rs.getInt("seat"));
		ticket.setTicketPrice(rs.getInt("ticketPrice"));
		ticket.setIsPurchased(rs.getBoolean("isPurchased"));
		return ticket;
	}

}
