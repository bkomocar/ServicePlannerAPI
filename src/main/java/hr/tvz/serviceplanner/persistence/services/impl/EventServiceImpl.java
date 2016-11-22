package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.EventDto;
import hr.tvz.serviceplanner.dtos.EventDtoFactory;
import hr.tvz.serviceplanner.dtos.request.CreateByIdDto;
import hr.tvz.serviceplanner.dtos.request.CreateEventDto;
import hr.tvz.serviceplanner.dtos.request.UpdateEventDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.EventDao;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.EventService;

@Service
public class EventServiceImpl extends AbstractService<Event> implements EventService {

	@Autowired
	private EventDao dao;

	@Override
	public IdDto createEvent(Long id, CreateEventDto model) {
		Long eventId = dao.createEvent(id, CreateEventDto.toEvent(model));
		if (eventId != null) {
			return new IdDto(eventId);
		}
		return null;
	}

	@Override
	public boolean updateEvent(Long id, UpdateEventDto model) {
		if (model != null) {
			return dao.updateEvent(id, UpdateEventDto.toEvent(model));
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
	public EventDto getEvent(Long eventId) {
		Event event = dao.getEvent(eventId);
		if (event != null) {
			return EventDtoFactory.toEventDto(event, DtoType.large);
		}
		return null;
	}

	@Override
	public boolean removePurchase(Long eventId, Long purchaseId) {
		return dao.removePurchase(eventId, purchaseId);
	}

	@Override
	public boolean addPurchase(Long eventId, CreateByIdDto model) {
		return dao.addPurchase(eventId, model.getId());
	}

}
