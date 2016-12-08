package ua.epam.spring.hometask.dataManipulation;

import java.util.List;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.domain.UserAccount;

public interface AccountsDao {
	public void save(UserAccount account);

	public void delete(Integer id);

	public UserAccount getById(Integer id);
	
	public UserAccount getByUser(User user);

	public List<UserAccount> getAll();

	public void update(Integer id, double money);
}
