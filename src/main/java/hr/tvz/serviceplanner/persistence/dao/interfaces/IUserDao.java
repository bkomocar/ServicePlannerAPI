package hr.tvz.serviceplanner.persistence.dao.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.IOperations;
import hr.tvz.serviceplanner.persistence.models.User;

public interface IUserDao extends IOperations<User>{

	public User findByName(final String name);
}
