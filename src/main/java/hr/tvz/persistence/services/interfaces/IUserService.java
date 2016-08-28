package hr.tvz.persistence.services.interfaces;

import hr.tvz.persistence.dao.common.IOperations;
import hr.tvz.persistence.models.User;

public interface IUserService extends IOperations<User> {

	public boolean isUserValid(String name);
}
