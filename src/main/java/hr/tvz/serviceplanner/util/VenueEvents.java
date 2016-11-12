package hr.tvz.serviceplanner.util;

import java.sql.Time;
import java.util.List;

import hr.tvz.serviceplanner.persistence.models.Event;

public class VenueEvents {

	Time openTime;
	Time closeTime;
	List<Event> events;
	
	public VenueEvents(Time openTime, Time closeTime) {
		super();
		this.openTime = openTime;
		this.closeTime = closeTime;
	}
	public Time getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Time openTime) {
		this.openTime = openTime;
	}
	public Time getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	
	
}
