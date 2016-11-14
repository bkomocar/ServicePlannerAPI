package hr.tvz.serviceplanner.persistence.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.GroupDao;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.persistence.models.User;
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
			/*
			 * SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
			 * Date minDate; try { minDate = formatter.parse(date); Criteria
			 * crit = getCurrentSession().createCriteria(Event.class); Date
			 * maxDate = new Date(minDate.getTime() +
			 * TimeUnit.DAYS.toMillis(1)); Conjunction and =
			 * Restrictions.conjunction(); and.add( Restrictions.ge("startTime",
			 * minDate) ); and.add( Restrictions.lt("startTime", maxDate) );
			 * crit.add(and); List<?> results = crit.list();
			 * venueEvents.setEvents((List<Event>) results); return venueEvents;
			 * } catch (ParseException e) { e.printStackTrace(); }
			 */

			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date fromDate = df.parse(date + " 00:00:00");
				Date toDate = df.parse(date + " 23:59:59");
				Criteria criteria = getCurrentSession().createCriteria(Event.class);
				criteria.add(Restrictions.between("startTime", fromDate, toDate));
				criteria.add(Restrictions.eq("venue", venue));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				//criteria.setProjection(Projections.distinct(Projections.property("id")));
				//criteria.setResultTransformer(Transformers.aliasToBean(Event.class));
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
