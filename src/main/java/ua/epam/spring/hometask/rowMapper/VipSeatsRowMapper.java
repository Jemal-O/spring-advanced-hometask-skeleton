package ua.epam.spring.hometask.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.epam.spring.hometask.domain.VipSeats;

public class VipSeatsRowMapper implements RowMapper<VipSeats> {

	@Override
	public VipSeats mapRow(ResultSet rs, int num) throws SQLException {
		VipSeats vipSeats = new VipSeats();
		vipSeats.setId(rs.getInt("id"));
		vipSeats.setAuditoriumId(rs.getInt("auditoriumId"));
		vipSeats.setPlace(rs.getString("place"));
		return vipSeats;
	}

}
