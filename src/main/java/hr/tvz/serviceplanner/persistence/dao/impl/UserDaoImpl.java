package hr.tvz.serviceplanner.persistence.dao.impl;

import java.util.SortedSet;

import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.UserDao;
import hr.tvz.serviceplanner.persistence.models.User;
import hr.tvz.serviceplanner.persistence.models.Venue;

@Repository
public class UserDaoImpl extends AbstractHibernateDao<User> implements UserDao {

	public UserDaoImpl() {
		super();
		setClazz(User.class);
	}
	
	@Override
	public boolean hasUserRightsOnVenue(Long userId, Venue venue) {
		
		User u = getCurrentSession().get(User.class, userId);
		SortedSet<Venue> venues = u.getVenues();
		if (venues.contains(venue)){
			return true;
		}
		return false;
		
	}
}
