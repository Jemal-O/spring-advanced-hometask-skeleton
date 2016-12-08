package ua.epam.spring.hometask.dataManipulationImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.epam.spring.hometask.dataManipulation.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.rowMapper.AuditoriumRowMapper;

@Repository
public class AuditoriumDaoImpl implements AuditoriumDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Auditorium auditorium) {
		jdbcTemplate.update("INSERT INTO auditoriums (name, numberOfSeats, isAssign) VALUES (?,?,?)", 
				auditorium.getName(), 
				auditorium.getNumberOfSeats(),
				auditorium.getIsAssign());
	}

	@Override
	public void delete(Auditorium auditorium) {
		jdbcTemplate.update("DELETE FROM auditoriums WHERE auditoriums.id = ?", auditorium.getId());

	}

	@Override
	public Auditorium getById(Integer id) {
		return jdbcTemplate.queryForObject("SELECT * FROM auditoriums WHERE auditoriums.id = ?", new Object[] { id },
				new AuditoriumRowMapper());
	}

	@Override
	public List<Auditorium> getAll() {
		return jdbcTemplate.query("SELECT * FROM auditoriums", new AuditoriumRowMapper());
	}

	@Override
	public void update(Auditorium auditorium) {
		jdbcTemplate.update("UPDATE auditoriums SET name = ?, numberOfSeats=?, isAssign=? WHERE auditoriums.id = ?",
				auditorium.getName(), 
				auditorium.getNumberOfSeats(), 
				auditorium.getId(),
				auditorium.getIsAssign());
	}

	@Override
	public Auditorium getByName(String name) {
		return jdbcTemplate.queryForObject("SELECT * FROM auditoriums WHERE auditoriums.name = ?",
				new Object[] {name}, 
				new AuditoriumRowMapper());
	}

	@Override
	public List<Auditorium> getAllAssigning() {
		return jdbcTemplate.query("SELECT * FROM auditoriums WHERE isAssign=true", new AuditoriumRowMapper());
	}
	
	

}
