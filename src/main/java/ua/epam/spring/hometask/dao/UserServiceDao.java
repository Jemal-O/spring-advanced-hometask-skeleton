package ua.epam.spring.hometask.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.epam.spring.hometask.dataManipulation.UserDao;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

@Component
public class UserServiceDao implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User save(User object) {
		userDao.save(object);
		return object;
	}

	@Override
	public void remove(User object) {
		userDao.delete(object);

	}

	@Override
	public User getById(Integer id) {
		return userDao.getById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAll();
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public void addAll(List<User> users) {
		for (User user : users) {
			save(user);
		}

	}

}
