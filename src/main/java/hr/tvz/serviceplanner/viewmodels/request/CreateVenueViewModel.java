package hr.tvz.serviceplanner.viewmodels.request;

import java.io.Serializable;
import java.sql.Time;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import hr.tvz.serviceplanner.json.SqlTimeDeserializer;
import hr.tvz.serviceplanner.persistence.models.Venue;

public class CreateVenueViewModel implements Serializable {

	private static final long serialVersionUID = 545451L;

	@NotNull
	@Length(min = 5, max = 255)
	private String name;
	
	@NotNull
	@Length(min = 5, max = 255)
	private String owner;
	
	@Length(max = 255)
	private String description;
	
	@NotNull
	@JsonFormat(pattern = "HH:mm")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	private Time openTime;
	
	@NotNull
	@JsonFormat(pattern = "HH:mm")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	private Time closeTime;

	public static Venue toVenue(CreateVenueViewModel model) {
		if (model != null) {
		return new Venue(model.name, model.description, model.owner, model.openTime, model.closeTime);
	} 
		return null;
	}
	
	public CreateVenueViewModel() {
		super();
	}

	public CreateVenueViewModel(String name, String owner, String description) {
		super();
		this.name = name;
		this.owner = owner;
		this.description = description;
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
	
	

}
