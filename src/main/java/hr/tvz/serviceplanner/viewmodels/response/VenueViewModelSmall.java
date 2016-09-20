package hr.tvz.serviceplanner.viewmodels.response;

import hr.tvz.serviceplanner.viewmodels.VenueViewModel;

public class VenueViewModelSmall implements VenueViewModel {

	private Long id;
	private String name;

	public VenueViewModelSmall() {
		super();
	}

	public VenueViewModelSmall(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

}
