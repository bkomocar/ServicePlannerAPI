package hr.tvz.serviceplanner.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.GroupDao;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.persistence.models.Venue;

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

}
