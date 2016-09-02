package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.viewmodels.request.CreateEventViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateEventViewModel;
import hr.tvz.serviceplanner.viewmodels.response.EventViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

public interface EventService extends Operations<Event> {

	public IdViewModel createEvent(Long id, CreateEventViewModel model);

	public boolean updateEvent(Long id, UpdateEventViewModel model);

	public boolean deleteEvent(Long venueId, Long eventId);

	public EventViewModel getEvent(Long eventId);
	
	public boolean removePurchase(Long eventId, Long purchaseId);
}
