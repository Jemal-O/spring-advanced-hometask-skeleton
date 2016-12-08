package ua.epam.spring.hometask.dataManipulation;

import java.util.List;

import ua.epam.spring.hometask.domain.User;

public interface UserDao {

	public void save(User user);

	public void delete(User user);

	public User getById(Integer id);

	public List<User> getAll();

	public void update(User user);

	public User getUserByEmail(String email);
}
