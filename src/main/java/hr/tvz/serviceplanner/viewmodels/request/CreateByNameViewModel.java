package hr.tvz.serviceplanner.viewmodels.request;

import org.hibernate.validator.constraints.NotBlank;

public class CreateByNameViewModel {

	@NotBlank
	private String name;

	public CreateByNameViewModel() {
		super();
	}

	public CreateByNameViewModel(String name) {
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