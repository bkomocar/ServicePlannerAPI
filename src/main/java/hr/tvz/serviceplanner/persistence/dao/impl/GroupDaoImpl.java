package hr.tvz.serviceplanner.persistence.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.GroupDao;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.util.VenueEvents;

@Repository
public class GroupDaoImpl extends AbstractHibernateDao<Group> implements GroupDao {

	public GroupDaoImpl() {
		super();
		setClazz(Group.class);
	}

	@Override
	public Long createGroup(Long id, Group group) {
		Venue venue = getCurrentSession().get(Venue.class, id);
		if (venue != null) {
			group.setVenue(venue);
			create(group);
			return group.getId();
		}
		return null;
	}

	@Override
	public boolean updateGroup(Long id, Group group) {
		Group originalGroup = findOne(id);
		if (originalGroup != null) {
			if (group.getName() != null) {
				originalGroup.setName(group.getName());
			}
			if (group.getType() != null) {
				originalGroup.setType(group.getType());
			}
			update(originalGroup);
			return true;
		}
		return false;
	}

	@Override
	public SortedSet<Category> getCategoriesForGroup(Long venueId, Long groupId) {
		Group group = findOne(groupId);
		if (group.getVenue().getId().equals(venueId)) {
			return group.getCategories();
		}
		return null;
	}

	@Override
	public VenueEvents getTimeEventsForServiceGroupByDate(long id, long groupId, String date) {
		Group group = findOne(groupId);
		Venue venue = group.getVenue();
		if (venue.getId().equals(id)) {
			VenueEvents venueEvents = new VenueEvents(venue.getOpenTime(), venue.getCloseTime());
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date fromDate = df.parse(date + " 00:00:00");
				Date toDate = df.parse(date + " 23:59:59");
				Criteria criteria = getCurrentSession().createCriteria(Event.class);
				criteria.add(Restrictions.between("startTime", fromDate, toDate));
				criteria.add(Restrictions.eqOrIsNull("group", group));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				List<?> results = criteria.list();
				venueEvents.setEvents((List<Event>) results);
				return venueEvents;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
