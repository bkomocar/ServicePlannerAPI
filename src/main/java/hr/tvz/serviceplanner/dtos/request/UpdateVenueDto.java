package hr.tvz.serviceplanner.dtos.request;

import java.sql.Time;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.util.SqlTimeDeserializer;

public class UpdateVenueDto {

	@Length(min = 1, max = 50, message = "Name length should be between {min} and {max} characters")
	private String name;

	@Length(min = 1, max = 100, message = "Owner length should be between {min} and {max} characters")
	private String owner;

	@Length(max = 500, message = "Description can not be longer than {max} characters")
	private String description;

	@JsonFormat(pattern = "HH:mm")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	private Time openTime;

	@JsonFormat(pattern = "HH:mm")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	private Time closeTime;

	public static Venue toVenue(UpdateVenueDto model) {
		if (model != null) {
			return new Venue(model.name, model.description, model.owner, model.openTime, model.closeTime);
		}
		return null;
	}

	public UpdateVenueDto() {
		super();
	}

	public UpdateVenueDto(String name, String owner, String description, Time openTime, Time closeTime) {
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
