package hr.tvz.serviceplanner.persistence.dao.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.User;

public interface UserDao extends Operations<User>{

	public User findByName(final String name);
}
