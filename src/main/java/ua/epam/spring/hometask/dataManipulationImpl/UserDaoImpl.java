package ua.epam.spring.hometask.dataManipulationImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.epam.spring.hometask.dataManipulation.UserDao;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.rowMapper.UserRowMapper;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(User user) {
		jdbcTemplate.update("INSERT INTO users (firstName, lastName, email, password, roles) VALUES (?,?,?,?,?)",
				user.getFirstName(), 
				user.getLastName(), 
				user.getEmail(), 
				user.getPassword(), 
				user.getRoles());
	}

	@Override
	public void delete(User user) {
		jdbcTemplate.update("DELETE FROM users WHERE users.id = ?", user.getId());
	}

	@Override
	public User getById(Integer id) {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE users.id = ?", new Object[] { id },
				new UserRowMapper());
	}

	@Override
	public List<User> getAll() {
		return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
	}

	@Override
	public void update(User user) {
		jdbcTemplate.update("UPDATE users SET firstName = ?, lastName = ?, email = ? WHERE users.id = ?",
				user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
	}

	@Override
	public User getUserByEmail(String email) {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE users.email = ?", new Object[] { email },
				new UserRowMapper());
	}

}
