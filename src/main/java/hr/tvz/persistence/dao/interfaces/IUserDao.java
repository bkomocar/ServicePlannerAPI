package hr.tvz.persistence.dao.interfaces;

import hr.tvz.persistence.dao.common.IOperations;
import hr.tvz.persistence.models.User;

public interface IUserDao extends IOperations<User>{

	public User findByName(final String name);
}
