package ua.epam.spring.hometask.dataManipulationImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.epam.spring.hometask.dataManipulation.AccountsDao;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.domain.UserAccount;
import ua.epam.spring.hometask.rowMapper.AccountRowMapper;

@Repository
public class AccountsDataImpl implements AccountsDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(UserAccount account) {
		jdbcTemplate.update("INSERT INTO accounts (userId, money) VALUES (?,?)", account.getUserId(),
				account.getMoney());

	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("DELETE FROM accounts WHERE accounts.id = ?", id);
	}

	@Override
	public UserAccount getById(Integer id) {
		return jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE accounts.id = ?", new Object[] { id },
				new AccountRowMapper());
	}

	@Override
	public List<UserAccount> getAll() {
		return jdbcTemplate.query("SELECT * FROM accounts", new AccountRowMapper());
	}

	@Override
	public void update(Integer id, double money) {
		jdbcTemplate.update("UPDATE accounts SET money=? WHERE accounts.id = ?", money, id);

	}

	@Override
	public UserAccount getByUser(User user) {
		return jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE accounts.userId = ?",
				new Object[] { user.getId() }, new AccountRowMapper());
	}

}
