package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.UserDao;
import hr.tvz.serviceplanner.persistence.models.User;
import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;

@Service
public class UserRightsCheckerServiceImpl extends AbstractService<User> implements UserRightsCheckerService {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean hasUserRightsOnVenue(Long userId, Long venueId) {
		if (userId == null || venueId == null) {
			return false;
		}
		Venue venue = new Venue(venueId);
		return userDao.hasUserRightsOnVenue(userId, venue);
	}

	@Override
	protected Operations<User> getDao() {
		return userDao;
	}

}
