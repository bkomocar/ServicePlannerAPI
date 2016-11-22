package hr.tvz.serviceplanner.persistence.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.dtos.CategoryDto;
import hr.tvz.serviceplanner.dtos.CategoryDtoFactory;
import hr.tvz.serviceplanner.dtos.CustomerDtoFactory;
import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.GroupDto;
import hr.tvz.serviceplanner.dtos.GroupDtoFactory;
import hr.tvz.serviceplanner.dtos.request.CreateGroupDto;
import hr.tvz.serviceplanner.dtos.request.UpdateGroupDto;
import hr.tvz.serviceplanner.dtos.response.EventDtoMedium;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.dtos.response.TimespanEventDto;
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
	public IdDto createGroup(Long id, CreateGroupDto model) {
		Long groupId = dao.createGroup(id, CreateGroupDto.toGroup(model));
		if (groupId != null) {
			return new IdDto(groupId);
		}
		return null;
	}

	@Override
	public boolean updateGroup(Long id, UpdateGroupDto model) {
		if (model != null) {
			return dao.updateGroup(id, UpdateGroupDto.toGroup(model));
		}
		return false;
	}

	@Override
	public GroupDto getGroup(Long id, DtoType type) {
		Group group = dao.findOne(id);
		if (group != null) {
			return GroupDtoFactory.toGroupDto(group, type);
		}
		return null;
	}

	@Override
	public List<CategoryDto> getCategoriesForGroup(Long venueId, Long groupId, DtoType type) {
		SortedSet<Category> categories = dao.getCategoriesForGroup(venueId, groupId);
		if (categories != null) {
			return CategoryDtoFactory.toCategoryDto(new ArrayList<Category>(categories), type);
		}
		return null;
	}

	@Override
	public List<TimespanEventDto> getTimeEventsForGroupByDate(long id, long groupId, String date) {
		VenueEvents events = dao.getTimeEventsForServiceGroupByDate(id, groupId, date);
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		SimpleDateFormat hourMinsFormat = new SimpleDateFormat("HH:mm");
		int openTimeHours = Integer.parseInt(sdf.format(events.getOpenTime()));
		int closeTimeHours = Integer.parseInt(sdf.format(events.getCloseTime()));
		int tempTime = openTimeHours;
		List<TimespanEventDto> eventdtos = new ArrayList<>();
		do {
			TimespanEventDto timespan = new TimespanEventDto(
					(tempTime < 10) ? "0" + tempTime + ":00" : tempTime + ":00");
			if (tempTime == 24) {
				tempTime = 0;
			}
			eventdtos.add(timespan);
			tempTime++;
		} while (tempTime != closeTimeHours);

		for (Event event : events.getEvents()) {
			List<Customer> customers = new ArrayList<>();
			for (Purchase purchase : event.getPurchases()) {
				customers.add(purchase.getCustomer());
			}

			EventDtoMedium eventDto = new EventDtoMedium(event.getId());

			if (event.getEmployee() != null) {
				eventDto.setEmployeeId(event.getEmployee().getId());
			}
			if (event.getProduct() != null) {
				eventDto.setProductId(event.getProduct().getId());
				eventDto.setProductShortName(event.getProduct().getShortName());
				if (event.getProduct().getCategory() != null) {
					eventDto.setCategoryColor(event.getProduct().getCategory().getColor());
				}
			}
			if (event.getStartTime() != null) {
				eventDto.setStartTime(hourMinsFormat.format(event.getStartTime()));
			}
			if (event.getEndTime() != null) {
				eventDto.setEndTime(hourMinsFormat.format(event.getEndTime()));
			}
			if (customers != null) {
				eventDto.setCustomers(CustomerDtoFactory.toCustomerDto(customers, DtoType.small));
			}
			int time = Integer.parseInt(sdf.format(event.getStartTime()));
			int index = time - openTimeHours;
			if (time < openTimeHours) {
				index = 24 - openTimeHours + time;
			}
			eventdtos.get(index).getEvents().add(eventDto);
		}
		return eventdtos;
	}

}
