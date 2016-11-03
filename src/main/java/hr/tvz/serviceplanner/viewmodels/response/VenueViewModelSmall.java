package hr.tvz.serviceplanner.viewmodels.response;

import hr.tvz.serviceplanner.enums.VenueType;
import hr.tvz.serviceplanner.viewmodels.VenueViewModel;

public class VenueViewModelSmall implements VenueViewModel {

	private Long id;
	private String name;
	private VenueType type;

	public VenueViewModelSmall() {
		super();
	}

	public VenueViewModelSmall(Long id, String name, VenueType type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
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

	public VenueType getType() {
		return type;
	}

	public void setType(VenueType type) {
		this.type = type;
	}

}
