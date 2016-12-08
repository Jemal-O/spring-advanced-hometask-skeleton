package ua.epam.spring.hometask.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.domain.UserAccount;
import ua.epam.spring.hometask.service.AccountsService;
import ua.epam.spring.hometask.service.UserService;

@Controller
public class AccountsController {
	private static final String SUCCESS_REFILLING = "Balance was successfully refilled";
	private static final String SUCCESS_REMOVING = "Account was successfully removed";

	@Autowired
	private AccountsService accountsService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public ModelAndView goToAccountsAdmin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("accounts");
		User user = userService.getUserByEmail(request.getUserPrincipal().getName());
		double balance = accountsService.getByUser(user).getMoney();
		mav.addObject("user", request.getUserPrincipal().getName());
		mav.addObject("accounts", accountsService.getAll());
		mav.addObject("balance", balance);
		return mav;
	}

	@RequestMapping(value = "/accounts/account/{accountId}", method = RequestMethod.GET)
	public ModelAndView getAccountsById(@PathVariable Integer accountId) {
		ModelAndView modelAndView = new ModelAndView("account");
		UserAccount userAccount = accountsService.getById(accountId);
		return modelAndView.addObject("account", userAccount.toString());
	}

	@RequestMapping(value = "/accounts/updateAccount", method = RequestMethod.POST)
	public ModelAndView saveAccount(@RequestParam double money, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("output");
		User user = userService.getUserByEmail(request.getUserPrincipal().getName());
		double balance = accountsService.getByUser(user).getMoney();
		accountsService.update(user.getId(), balance + money);
		return modelAndView.addObject("output", SUCCESS_REFILLING);
	}

	@RequestMapping(value = "/accounts/get-all/pdf", method = RequestMethod.GET, headers = "accept=application/pdf")
	public ModelAndView getAllUsersAccount() {
		Map<String, String> data = new LinkedHashMap<String, String>();
		data.put("ID", "Account");
		for (UserAccount uAccount : accountsService.getAll()) {
			data.put(uAccount.getId().toString(), uAccount.toString());
		}
		return new ModelAndView("PdfViewResolver", "data", data);
	}
}
