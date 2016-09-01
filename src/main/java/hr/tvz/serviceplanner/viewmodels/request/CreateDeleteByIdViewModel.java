package hr.tvz.serviceplanner.viewmodels.request;

import javax.validation.constraints.NotNull;

public class CreateDeleteByIdViewModel {

	@NotNull
	Long id;

	public CreateDeleteByIdViewModel() {
		super();
	}

	public CreateDeleteByIdViewModel(Long id) {
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
