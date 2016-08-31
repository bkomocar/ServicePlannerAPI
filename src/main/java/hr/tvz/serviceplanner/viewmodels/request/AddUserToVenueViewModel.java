package hr.tvz.serviceplanner.viewmodels.request;

public class AddUserToVenueViewModel {

	private String name;

	public AddUserToVenueViewModel() {
		super();
	}

	public AddUserToVenueViewModel(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
