package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.EventDao;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.EventService;
import hr.tvz.serviceplanner.viewmodels.request.CreateByIdViewModel;
import hr.tvz.serviceplanner.viewmodels.request.CreateEventViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateEventViewModel;
import hr.tvz.serviceplanner.viewmodels.response.EventViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@Service
public class EventServiceImpl extends AbstractService<Event> implements EventService {

	@Autowired
	private EventDao dao;

	@Override
	public IdViewModel createEvent(Long id, CreateEventViewModel model) {
		Long eventId = dao.createEvent(id, CreateEventViewModel.toEvent(model));
		if (eventId != null) {
			return new IdViewModel(eventId);
		}
		return null;
	}

	@Override
	public boolean updateEvent(Long id, UpdateEventViewModel model) {
		if (model != null) {
			return dao.updateEvent(id, UpdateEventViewModel.toEvent(model));
		}
		return false;
	}

	@Override
	public boolean deleteEvent(Long venueId, Long eventId) {
		return dao.deleteEvent(venueId, eventId);
	}

	@Override
	protected Operations<Event> getDao() {
		return dao;
	}

	@Override
	public EventViewModel getEvent(Long eventId) {
		Event event = dao.getEvent(eventId);
		if (event != null) {
			return EventViewModel.fromEvent(event);
		}
		return null;
	}

	@Override
	public boolean removePurchase(Long eventId, Long purchaseId) {
		return dao.removePurchase(eventId, purchaseId);
	}

	@Override
	public boolean addPurchase(Long eventId, CreateByIdViewModel model) {
		return dao.addPurchase(eventId, model.getId());
	}

}
