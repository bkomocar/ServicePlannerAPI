package hr.tvz.serviceplanner.viewmodels.request;

import javax.validation.constraints.NotNull;

public class CreateByIdViewModel {

	@NotNull(message = "Id is a required field")
	Long id;

	public CreateByIdViewModel() {
		super();
	}

	public CreateByIdViewModel(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
