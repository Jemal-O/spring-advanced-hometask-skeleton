package ua.epam.spring.hometask.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

@Controller
public class UserController {
	private static final String SUCCESS_SAVING = "Users was successfully saved";
	private static final String SUCCESS_REMOVING = "Users was successfully removed";
	@Autowired
	private UserService usersService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView goToUsersAdmin() {
		ModelAndView mav = new ModelAndView("users");
		mav.addObject("users", usersService.getAllUsers());
		return mav;
	}

	@RequestMapping(value = "/users/remove/{email}", method = RequestMethod.GET)
	public ModelAndView removeUser(@PathVariable String email) {
		usersService.remove(usersService.getUserByEmail(email));
		ModelAndView modelAndView = new ModelAndView("output");
		return modelAndView.addObject("output", SUCCESS_REMOVING);

	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public ModelAndView getUserById(@PathVariable int userId) {
		ModelAndView modelAndView = new ModelAndView("user");
		User user = usersService.getById(userId);
		return modelAndView.addObject("user", user.toString());
	}

	@RequestMapping(value = "/users/user/{email}", method = RequestMethod.GET)
	public ModelAndView getUserByEmail(@PathVariable String email) {
		ModelAndView modelAndView = new ModelAndView("user");
		User user = usersService.getUserByEmail(email);
		return modelAndView.addObject("user", user);
	}

	@RequestMapping(value = "/users/get-all/pdf", method = RequestMethod.GET, headers = "accept=application/pdf")
	public ModelAndView getAllUsers() {
		Map<String, String> data = new LinkedHashMap<String, String>();
		data.put("ID", "User");
		for (User user : usersService.getAllUsers()) {
			data.put(user.getId().toString(), user.toString());
		}
		return new ModelAndView("PdfViewResolver", "data", data);
	}

	@RequestMapping(value = "/users/user/create/", method = RequestMethod.POST)
	public ModelAndView createAndSaveAuditorium(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String email, @RequestParam String password, @RequestParam String roles) {
		usersService.save(new User(firstName, lastName, email, generatePassword(password + email), roles));
		ModelAndView modelAndView = new ModelAndView("output");
		return modelAndView.addObject("output", SUCCESS_SAVING);
	}

	private String generatePassword(String password) {
		String passHash = DigestUtils.md5Hex(password);
		return passHash;
	}

}
