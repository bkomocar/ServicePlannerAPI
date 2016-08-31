package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.viewmodels.request.UpdateGroupViewModel;
import hr.tvz.serviceplanner.viewmodels.response.GroupViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

public interface GroupService  extends Operations<Group> {
	
	public GroupViewModel getGroup(Long id);
	
	public IdViewModel createGroup(Long id, Group group);
	
	public boolean updateGroup(Long id, UpdateGroupViewModel model);
	
}
