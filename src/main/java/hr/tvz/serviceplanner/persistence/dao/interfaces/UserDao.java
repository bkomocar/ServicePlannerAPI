package hr.tvz.serviceplanner.persistence.dao.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.User;
import hr.tvz.serviceplanner.persistence.models.Venue;

public interface UserDao extends Operations<User> {

	public boolean hasUserRightsOnVenue(Long userId, Venue venue);

	public boolean updateUser(Long userId, User user);
}
