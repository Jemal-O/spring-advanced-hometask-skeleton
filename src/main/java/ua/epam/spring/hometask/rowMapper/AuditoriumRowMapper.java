package ua.epam.spring.hometask.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.epam.spring.hometask.domain.Auditorium;

public class AuditoriumRowMapper implements RowMapper<Auditorium> {

	@Override
	public Auditorium mapRow(ResultSet rs, int rowNum) throws SQLException {
		Auditorium auditorium = new Auditorium();
		auditorium.setId(rs.getInt("id"));
		auditorium.setName(rs.getString("name"));
		auditorium.setNumberOfSeats(rs.getInt("numberOfSeats"));
		auditorium.setAssign(rs.getBoolean("isAssign"));
		return auditorium;
	}

}