package hr.tvz.serviceplanner.persistence.dao.interfaces;

import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.util.VenueEvents;

public interface GroupDao extends Operations<Group> {

	public boolean updateGroup(Long id, Group group);

	public SortedSet<Category> getCategoriesForGroup(Long venueId, Long groupId);

	public Long createGroup(Long id, Group group);

	public VenueEvents getTimeEventsForGroupByDate(long id, long groupId, String date);
}
