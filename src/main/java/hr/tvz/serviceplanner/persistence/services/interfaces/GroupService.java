package hr.tvz.serviceplanner.persistence.services.interfaces;

import java.util.Date;
import java.util.List;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.viewmodels.CategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.GroupViewModel;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;
import hr.tvz.serviceplanner.viewmodels.request.CreateGroupViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateGroupViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;
import hr.tvz.serviceplanner.viewmodels.response.TimespanEventViewModel;

public interface GroupService extends Operations<Group> {

	public GroupViewModel getGroup(Long id, ViewModelType type);

	public List<CategoryViewModel> getCategoriesForGroup(Long id, Long groupId, ViewModelType type);

	public IdViewModel createGroup(Long id, CreateGroupViewModel model);

	public boolean updateGroup(Long id, UpdateGroupViewModel model);

	public List<TimespanEventViewModel> getTimeEventsForGroupByDate(long id, long groupId, String date);
}
