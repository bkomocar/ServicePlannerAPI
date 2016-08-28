package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.User;

public interface UserService extends Operations<User> {

	public boolean isUserValid(String name);
	
	public User saveUser(User user);
}
