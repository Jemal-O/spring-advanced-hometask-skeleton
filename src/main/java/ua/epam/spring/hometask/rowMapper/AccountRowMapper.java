package ua.epam.spring.hometask.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.epam.spring.hometask.domain.UserAccount;

public class AccountRowMapper implements RowMapper<UserAccount> {

	@Override
	public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new UserAccount(rs.getInt("id"), 
				rs.getInt("userId"), 
				rs.getDouble("money"));
	}

}
