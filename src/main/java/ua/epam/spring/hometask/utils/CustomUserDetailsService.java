package ua.epam.spring.hometask.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ua.epam.spring.hometask.domain.CustomUserDetails;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.getUserByEmail(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User with Login " + userName + " not found");
		}
		return new CustomUserDetails(user);
	}
}
