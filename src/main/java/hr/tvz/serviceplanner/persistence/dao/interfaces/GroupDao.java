package hr.tvz.serviceplanner.persistence.dao.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Group;

public interface GroupDao extends Operations<Group> {

	public boolean updateGroup(Long id, Group group);

	public Long createGroup(Long id, Group group);
}
