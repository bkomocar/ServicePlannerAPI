package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.IOperations;
import hr.tvz.serviceplanner.persistence.models.User;

public interface IUserService extends IOperations<User> {

	public boolean isUserValid(String name);
}
