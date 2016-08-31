package hr.tvz.serviceplanner.viewmodels.request;

import java.sql.Time;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import hr.tvz.serviceplanner.json.SqlTimeDeserializer;
import hr.tvz.serviceplanner.persistence.models.Venue;

public class UpdateVenueViewModel {

	@Length(min = 5, max = 255)
	private String name;

	@Length(min = 5, max = 255)
	private String owner;

	@Length(max = 255)
	private String description;

	@JsonFormat(pattern = "HH:mm")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	private Time openTime;

	@JsonFormat(pattern = "HH:mm")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	private Time closeTime;

	public static Venue toVenue(UpdateVenueViewModel model) {
		if (model != null) {
			return new Venue(model.name, model.description, model.owner, model.openTime, model.closeTime);
		}
		return null;
	}

	public UpdateVenueViewModel() {
		super();
	}

	public UpdateVenueViewModel(String name, String owner, String description, Time openTime, Time closeTime) {
		super();
		this.name = name;
		this.owner = owner;
		this.description = description;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
