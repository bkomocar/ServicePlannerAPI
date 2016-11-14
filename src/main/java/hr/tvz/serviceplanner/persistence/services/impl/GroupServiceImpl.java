package hr.tvz.serviceplanner.persistence.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.GroupDao;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.persistence.models.Purchase;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.GroupService;
import hr.tvz.serviceplanner.util.VenueEvents;
import hr.tvz.serviceplanner.viewmodels.CategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.CategoryViewModelFactory;
import hr.tvz.serviceplanner.viewmodels.CustomerViewModelFactory;
import hr.tvz.serviceplanner.viewmodels.GroupViewModel;
import hr.tvz.serviceplanner.viewmodels.GroupViewModelFactory;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;
import hr.tvz.serviceplanner.viewmodels.request.CreateGroupViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateGroupViewModel;
import hr.tvz.serviceplanner.viewmodels.response.EventViewModelMedium;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;
import hr.tvz.serviceplanner.viewmodels.response.TimespanEventViewModel;

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
			return GroupViewModelFactory.toGroupViewModel(group, type);
		}
		return null;
	}

	@Override
	public List<CategoryViewModel> getCategoriesForGroup(Long venueId, Long groupId, ViewModelType type) {
		SortedSet<Category> categories = dao.getCategoriesForGroup(venueId, groupId);
		if (categories != null) {
			return CategoryViewModelFactory.toCategoryViewModel(new ArrayList<Category>(categories), type);
		}
		return null;
	}

	@Override
	public List<TimespanEventViewModel> getTimeEventsForGroupByDate(long id, long groupId, String date) {
		VenueEvents events = dao.getTimeEventsForServiceGroupByDate(id, groupId, date);
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		SimpleDateFormat hourMinsFormat = new SimpleDateFormat("HH:mm");
		int openTimeHours = Integer.parseInt(sdf.format(events.getOpenTime()));
		int closeTimeHours = Integer.parseInt(sdf.format(events.getCloseTime()));
		int tempTime = openTimeHours;
		List<TimespanEventViewModel> eventViewModels = new ArrayList<>();
		do {
			TimespanEventViewModel timespan = new TimespanEventViewModel(
					(tempTime < 10) ? "0" + tempTime + ":00" : tempTime + ":00");
			if (tempTime == 24) {
				tempTime = 0;
			}
			eventViewModels.add(timespan);
			tempTime++;				
		} while (tempTime != closeTimeHours);

		for (Event event : events.getEvents()) {
			List<Customer> customers = new ArrayList<>();
			for (Purchase purchase : event.getPurchases()) {
				customers.add(purchase.getCustomer());
			}
			EventViewModelMedium eventViewModel = new EventViewModelMedium(event.getId(), event.getEmployee().getId(),
					event.getProduct().getCategory().getColor(), event.getProduct().getShortName(),
					hourMinsFormat.format(event.getStartTime()), hourMinsFormat.format(event.getEndTime()),
					CustomerViewModelFactory.toCustomerViewModel(customers, ViewModelType.small));
			int time = Integer.parseInt(sdf.format(event.getStartTime()));
			int index = time - openTimeHours;
			if (time < openTimeHours) {
				index = 24 - openTimeHours + time;
			}
			eventViewModels.get(index).getEvents().add(eventViewModel);
		}
		return eventViewModels;
	}

}
