package hr.tvz.serviceplanner.viewmodels.response;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Venue;

public class VenueViewModel {
	
	private Long id;
	private String name;
	private String description;
	private String owner;
	private String openTime;
	private String closeTime;
	
	public VenueViewModel() {
		super();
	}

	public VenueViewModel(Long id, String name, String description, String owner, String openTime, String closeTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}

	public static VenueViewModel fromVenue (Venue venue) {
		
		if(venue != null){
			return new VenueViewModel(venue.getId(), venue.getName(), venue.getDescription(), venue.getOwner(), venue.getOpenTime().toString(), venue.getCloseTime().toString());
		}
		
		return null;
	}
	
	public static List <VenueViewModel> fromVenue (List <Venue> venues) {
		
		if(venues != null){
			return venues.stream().map(v -> VenueViewModel.fromVenue(v)).collect(Collectors.toList());
		}
		
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	
}
