package hr.tvz.serviceplanner.viewmodels.request;

import java.io.Serializable;
import java.sql.Time;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import hr.tvz.serviceplanner.enums.VenueType;
import hr.tvz.serviceplanner.json.SqlTimeDeserializer;
import hr.tvz.serviceplanner.persistence.models.Venue;

public class CreateVenueViewModel implements Serializable {

	private static final long serialVersionUID = 545451L;

	@NotBlank(message = "Name field can not be empty")
	@Length(max = 50, message = "Name can not be longer than {max} characters")
	private String name;
	
	@NotBlank(message = "Owner field can not be empty")
	@Length(max = 100, message = "Owner can not be longer than {max} characters")
	private String owner;
	
	@Length(max = 500, message = "Description can not be longer than {max} characters")
	private String description;
	
	@NotNull(message = "Open time is a required field")
	@JsonFormat(pattern = "HH:mm")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	private Time openTime;
	
	@NotNull(message = "Close time is a required field")
	@JsonFormat(pattern = "HH:mm")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	private Time closeTime;
	
	@NotNull(message = "Type is a required field")
	private VenueType type;

	public static Venue toVenue(CreateVenueViewModel model) {
		if (model != null) {
		return new Venue(model.name, model.description, model.owner, model.openTime, model.closeTime, model.type);
	} 
		return null;
	}
	
	public CreateVenueViewModel() {
		super();
	}
	
	public CreateVenueViewModel(String name, String owner, String description, Time openTime, Time closeTime,
			VenueType type) {
		super();
		this.name = name;
		this.owner = owner;
		this.description = description;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.type = type;
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

	public VenueType getType() {
		return type;
	}

	public void setType(VenueType type) {
		this.type = type;
	}

}
