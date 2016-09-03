package hr.tvz.serviceplanner.persistence.dao.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Event;

public interface EventDao extends Operations<Event> {

	public Long createEvent(Long venueId, Event event);

	public boolean updateEvent(Long eventId, Event event);

	public boolean deleteEvent(Long venueId, Long eventId);

	public Event getEvent(Long eventId);
	
	public boolean addPurchase(Long eventId, Long purchaseId);
	
	public boolean removePurchase(Long eventId, Long purchaseId);
}
