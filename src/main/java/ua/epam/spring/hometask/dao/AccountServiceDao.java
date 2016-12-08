package ua.epam.spring.hometask.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.epam.spring.hometask.dataManipulation.AccountsDao;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.domain.UserAccount;
import ua.epam.spring.hometask.service.AccountsService;

@Component
public class AccountServiceDao implements AccountsService {
	@Autowired
	private AccountsDao accountsDao;

	@Override
	public void save(UserAccount account) {
		accountsDao.save(account);
	}

	@Override
	public void delete(Integer id) {
		accountsDao.delete(id);

	}

	@Override
	public UserAccount getById(Integer id) {
		return accountsDao.getById(id);
	}

	@Override
	public List<UserAccount> getAll() {
		return accountsDao.getAll();
	}

	@Override
	public void update(Integer id, double money) {
		accountsDao.update(id, money);
	}

	@Override
	public UserAccount getByUser(User user) {
		return accountsDao.getByUser(user);
	}

	@Override
	public void checkAndUpdateMoney(User user, Ticket ticket) throws Exception {
		Integer price = ticket.getTicketPrice();
		UserAccount account = getByUser(user);
		Double balance = account.getMoney() - price;
		account.setMoney(balance);
		update(account.getUserId(), account.getMoney());
		if (balance <= 0) {
			throw new Exception("Money is not enough! Rollback transaction.");
		}
	}

}
