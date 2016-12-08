package ua.epam.spring.hometask.dataManipulationImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.epam.spring.hometask.dataManipulation.VipSeatsDao;
import ua.epam.spring.hometask.domain.VipSeats;
import ua.epam.spring.hometask.rowMapper.VipSeatsRowMapper;

@Repository
public class VipSeatsDaoImpl implements VipSeatsDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(VipSeats vipSeats) {
		 jdbcTemplate.update("INSERT INTO vipSeats (id, place, auditoriumId) VALUES (?,?,?)",
	                null,
	                vipSeats.getPlace(),
	                vipSeats.getAuditoriumId());

	}

	@Override
	public void delete(VipSeats vipSeats) {
		jdbcTemplate.update("DELETE FROM vipSeats WHERE vipSeats.id = ?",
                vipSeats.getId());
	}

	@Override
	public VipSeats getById(Integer id) {
		 return jdbcTemplate.queryForObject("SELECT * FROM vipSeats WHERE vipSeats.id = ?",
	                new Object[] {id},
	                new VipSeatsRowMapper());
	}

	@Override
	public List<VipSeats> getAll(Integer auditoriumId) {
		 return jdbcTemplate.query("SELECT * FROM vipSeats WHERE vipSeats.auditoriumId=?",
				 	new Object[] {auditoriumId},
	                new VipSeatsRowMapper());
	}

	@Override
	public void update(VipSeats vipSeats) {
		jdbcTemplate.update("UPDATE vipSeats SET place = ?, auditoriumId = ? WHERE vipSeats.id = ?",
                vipSeats.getPlace(),
                vipSeats.getAuditoriumId());
				vipSeats.getId();
	}

}
