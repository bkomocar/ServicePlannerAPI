package hr.tvz.serviceplanner.viewmodels.response;

import interfaces.VenueViewModel;

public class VenueViewModelLarge extends VenueViewModel {

	private Long id;
	private String name;
	private String description;
	private String owner;
	private String openTime;
	private String closeTime;

	public VenueViewModelLarge() {
		super();
	}

	public VenueViewModelLarge(Long id, String name, String description, String owner, String openTime,
			String closeTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.openTime = openTime;
		this.closeTime = closeTime;
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
