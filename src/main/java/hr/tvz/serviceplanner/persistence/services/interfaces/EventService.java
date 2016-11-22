package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.dtos.EventDto;
import hr.tvz.serviceplanner.dtos.request.CreateByIdDto;
import hr.tvz.serviceplanner.dtos.request.CreateEventDto;
import hr.tvz.serviceplanner.dtos.request.UpdateEventDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Event;

public interface EventService extends Operations<Event> {

	public IdDto createEvent(Long id, CreateEventDto model);

	public boolean updateEvent(Long id, UpdateEventDto model);

	public boolean deleteEvent(Long venueId, Long eventId);

	public EventDto getEvent(Long eventId);

	public boolean removePurchase(Long eventId, Long purchaseId);

	public boolean addPurchase(Long eventId, CreateByIdDto model);
}
