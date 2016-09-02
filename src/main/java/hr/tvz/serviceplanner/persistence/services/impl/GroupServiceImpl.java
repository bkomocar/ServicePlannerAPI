package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.GroupDao;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.GroupService;
import hr.tvz.serviceplanner.viewmodels.GroupViewModel;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;
import hr.tvz.serviceplanner.viewmodels.request.CreateGroupViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateGroupViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@Service
public class GroupServiceImpl extends AbstractService<Group> implements GroupService {

	@Autowired
	private GroupDao dao;

	@Override
	protected Operations<Group> getDao() {
		return dao;
	}

	public GroupServiceImpl() {
		super();
	}

	@Override
	public IdViewModel createGroup(Long id, CreateGroupViewModel model) {
		Long groupId = dao.createGroup(id, CreateGroupViewModel.toGroup(model));
		if (groupId != null) {
			return new IdViewModel(groupId);
		}
		return null;
	}

	@Override
	public boolean updateGroup(Long id, UpdateGroupViewModel model) {
		if (model != null) {
			return dao.updateGroup(id, UpdateGroupViewModel.toGroup(model));
		}
		return false;
	}

	@Override
	public GroupViewModel getGroup(Long id, ViewModelType type) {
		Group group = dao.findOne(id);
		if (group != null) {
			return GroupViewModel.toGroupViewModel(group, type);
		}
		return null;
	}

}
