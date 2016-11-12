package hr.tvz.serviceplanner.viewmodels.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.viewmodels.GroupViewModel;

public class TimespanEventViewModel implements Comparable<TimespanEventViewModel> {

	String time;
	private List<EventViewModelMedium> events = new ArrayList<>();

	public TimespanEventViewModel(String time) {
		super();
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<EventViewModelMedium> getEvents() {
		return events;
	}

	public void setEvents(List<EventViewModelMedium> events) {
		this.events = events;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TimespanEventViewModel))
			return false;
		TimespanEventViewModel other = (TimespanEventViewModel) obj;
		if (time == null || other.time == null) {
			return false;
		} else if (!time.equals(other.time)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(TimespanEventViewModel o) {
		if (this.time != null && o.getTime() != null) {
			String time = o.getTime();
			return time.compareTo(this.time);
		}
		return -1;
	}

}
