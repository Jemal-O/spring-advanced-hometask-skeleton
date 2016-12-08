package ua.epam.spring.hometask.service;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.User;

/**
 * @author Yuriy_Tkach
 */
public interface UserService extends AbstractDomainObjectService<User> {

	/**
	 * Finding user by email
	 * 
	 * @param email
	 *            Email of the user
	 * @return found user or <code>null</code>
	 */
	public @Nullable User getUserByEmail(@Nonnull String email);

	public void addAll(List<User> users);
	
	public List<User> getAllUsers();

}
