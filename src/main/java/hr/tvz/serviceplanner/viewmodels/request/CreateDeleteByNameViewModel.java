package hr.tvz.serviceplanner.viewmodels.request;

import org.hibernate.validator.constraints.NotBlank;

public class CreateDeleteByNameViewModel {

	@NotBlank
	private String name;

	public CreateDeleteByNameViewModel() {
		super();
	}

	public CreateDeleteByNameViewModel(String name) {
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
