package ua.epam.spring.hometask.service;

import java.util.List;


import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.domain.UserAccount;


public interface AccountsService {

	public void save(UserAccount account);

	public void delete(Integer id);

	public UserAccount getById(Integer id);

	public List<UserAccount> getAll();

	public void update(Integer id, double money);

	public UserAccount getByUser(User user);

	public void checkAndUpdateMoney(User user, Ticket ticket) throws Exception;
}
