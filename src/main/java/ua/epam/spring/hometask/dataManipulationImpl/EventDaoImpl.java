package ua.epam.spring.hometask.dataManipulationImpl;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.epam.spring.hometask.dataManipulation.EventDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.rowMapper.EventRowMapper;

@Repository
public class EventDaoImpl implements EventDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Event event) {
		jdbcTemplate.update(
				"INSERT INTO events (id, name, airDateTime, basePrice, rating, auditoriumId) VALUES (?,?,?,?,?,?)",
				null, event.getName(),
				event.getAirDateTime().format(DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm:ss")), event.getBasePrice(),
				event.getRating().name(), event.getAuditoriumId());
	}

	@Override
	public void delete(Event event) {
		jdbcTemplate.update("DELETE FROM events WHERE events.id = ?", event.getId());
	}

	@Override
	public Event getById(Integer id) {
		return jdbcTemplate.queryForObject("SELECT * FROM events WHERE events.id = ?", new Object[] { id },
				new EventRowMapper());
	}

	@Override
	public List<Event> getAll() {
		return jdbcTemplate.query("SELECT * FROM events", new EventRowMapper());
	}

	@Override
	public void update(Event event) {
		jdbcTemplate.update(
				"UPDATE events SET id=? name = ?, airDateTime = ?, basePrice = ?, rating = ?, auditoriumId = ? WHERE events.id = ?",
				event.getName(), event.getAirDateTime().toString(), event.getBasePrice(), event.getRating().name(),
				event.getAuditoriumId(), event.getId());
	}

	@Override
	public Event getByName(String name) {
		return jdbcTemplate.queryForObject("SELECT * FROM events WHERE events.name = ?", new Object[] { name },
				new EventRowMapper());
	}
}
