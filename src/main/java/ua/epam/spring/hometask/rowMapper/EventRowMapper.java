package ua.epam.spring.hometask.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;

public class EventRowMapper implements RowMapper<Event> {

	@Override
	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event event = new Event();
		event.setId(rs.getInt("id"));
		event.setName(rs.getString("name"));
		event.setAirDateTime(LocalDateTime.parse(rs.getString("airDateTime").replace(" ", "T")));
		event.setBasePrice(rs.getDouble("basePrice"));
		event.setRating(EventRating.valueOf(rs.getString("rating")));
		event.setAuditoriumId(rs.getInt("auditoriumId"));
		return event;
	}

}
