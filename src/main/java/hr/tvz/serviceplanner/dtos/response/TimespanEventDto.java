package hr.tvz.serviceplanner.dtos.response;

import java.util.ArrayList;
import java.util.List;

public class TimespanEventDto implements Comparable<TimespanEventDto> {

	String time;
	private List<EventDtoMedium> events = new ArrayList<>();

	public TimespanEventDto(String time) {
		super();
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<EventDtoMedium> getEvents() {
		return events;
	}

	public void setEvents(List<EventDtoMedium> events) {
		this.events = events;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TimespanEventDto))
			return false;
		TimespanEventDto other = (TimespanEventDto) obj;
		if (time == null || other.time == null) {
			return false;
		} else if (!time.equals(other.time)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(TimespanEventDto o) {
		if (this.time != null && o.getTime() != null) {
			String time = o.getTime();
			return time.compareTo(this.time);
		}
		return -1;
	}

}
