package hr.tvz.serviceplanner.persistence.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
		if (venues.contains(venue)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUser(Long userId, User user) {
		User originalUser = findOne(userId);
		if (originalUser != null) {
			if (user.getName() != null) {
				Criteria crit = getCurrentSession().createCriteria(User.class);
				crit.add(Restrictions.eq("name", user.getName()));
				List<?> results = crit.list();
				if (results != null && !results.isEmpty()) {
					originalUser.setName(user.getName());
				}
			}
			if (user.getPassword() != null) {
				originalUser.setPassword(user.getPassword());
				originalUser.setLastPasswordReset(new Date());
			}
			if (user.getEmail() != null) {
				originalUser.setEmail(user.getEmail());
			}
			update(originalUser);
			return true;
		}
		return false;
	}
}
