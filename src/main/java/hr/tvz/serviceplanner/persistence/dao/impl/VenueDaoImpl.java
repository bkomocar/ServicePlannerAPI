package hr.tvz.serviceplanner.persistence.dao.impl;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.google.common.base.Preconditions;

import hr.tvz.serviceplanner.enums.GroupType;
import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.VenueDao;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.persistence.models.User;
import hr.tvz.serviceplanner.persistence.models.Venue;

@Repository
public class VenueDaoImpl extends AbstractHibernateDao<Venue> implements VenueDao {

	public VenueDaoImpl() {
		super();
		setClazz(Venue.class);
	}

	@Override
	public Venue saveVenue(Venue venue, Long userId) {
		Preconditions.checkNotNull(venue);
		Preconditions.checkNotNull(userId);
		User u = getCurrentSession().get(User.class, userId);
		TreeSet<User> users = new TreeSet<>();
		users.add(u);
		venue.setUsers(users);

		switch (venue.getType()) {
		case WINDSURF:
			Group courses = new Group("Courses", GroupType.SERVICE);
			Group rents = new Group("Rents", GroupType.RENT);
			TreeSet<Group> windsurfGroups = new TreeSet<>();
			windsurfGroups.add(courses);
			windsurfGroups.add(rents);
			venue.setGroups(windsurfGroups);
			break;
		default:
			Group services = new Group("Services", GroupType.SERVICE);
			Group products = new Group("Products", GroupType.SELL);
			services.setVenue(venue);
			products.setVenue(venue);
			TreeSet<Group> groups = new TreeSet<>();
			venue.setGroups(groups);
		}
		create(venue);

		for (Group group : venue.getGroups()) {
			group.setVenue(venue);
			getCurrentSession().saveOrUpdate(group);
		}
		return venue;
	}

	@Override
	public boolean addUser(Long venueId, String userName) {
		Preconditions.checkNotNull(venueId);
		Preconditions.checkNotNull(userName);
		Criteria crit = getCurrentSession().createCriteria(User.class);
		crit.add(Restrictions.eq("name", userName));
		List<?> results = crit.list();
		if (results != null && !results.isEmpty()) {
			if (getCurrentSession() != null) {
				Venue venue = findOne(venueId);
				SortedSet<User> users = venue.getUsers();
				User u = (User) results.get(0);
				users.add(u);
				venue.setUsers(users);
				update(venue);
				return true;
			}
		}
		return false;
	}

	@Override
	public SortedSet<Venue> getVenuesForUser(Long userId) {
		User u = getCurrentSession().get(User.class, userId);
		SortedSet<Venue> venues = new TreeSet<>();
		venues = u.getVenues();
		return venues;
	}

	@Override
	public boolean updateVenue(Long id, Venue venue) {
		Venue originalVenue = findOne(id);
		if (originalVenue != null) {
			if (venue.getName() != null) {
				originalVenue.setName(venue.getName());
			}
			if (venue.getDescription() != null) {
				originalVenue.setDescription(venue.getDescription());
			}
			if (venue.getOwner() != null) {
				originalVenue.setOwner(venue.getOwner());
			}
			if (venue.getOpenTime() != null) {
				originalVenue.setOpenTime(venue.getOpenTime());
			}
			if (venue.getCloseTime() != null) {
				originalVenue.setCloseTime(venue.getCloseTime());
			}
			update(originalVenue);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeUser(Long venueId, Long userId) {
		Venue venue = findOne(venueId);
		User user = getCurrentSession().get(User.class, userId);
		if (venue != null && user != null) {
			SortedSet<User> users = venue.getUsers();
			if (users != null && !users.isEmpty() && users.contains(user)) {
				users.remove(user);
				venue.setUsers(users);
				update(venue);
				return true;
			}
		}
		return false;
	}

	@Override
	public Group getGroup(Long venueId, String name) {
		Venue venue = findOne(venueId);
		SortedSet<Group> groups = venue.getGroups();
		for (Group group : groups) {
			if (group.getName().equalsIgnoreCase(name)) {
				return group;
			}
		}
		return null;
	}

	@Override
	public Venue getVenue(Long venueId) {
		Venue venue = findOne(venueId);
		return venue;
	}

}
