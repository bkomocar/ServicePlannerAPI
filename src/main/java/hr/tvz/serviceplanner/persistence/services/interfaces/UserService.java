package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.User;
import hr.tvz.serviceplanner.viewmodels.request.UpdateUserViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

public interface UserService extends Operations<User> {

	public boolean isUserValid(String name);
	
	public IdViewModel saveUser(User user);
	
	public boolean updateUser(Long userId, UpdateUserViewModel model);
}
