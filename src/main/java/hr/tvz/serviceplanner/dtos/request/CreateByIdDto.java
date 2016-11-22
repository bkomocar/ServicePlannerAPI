package hr.tvz.serviceplanner.dtos.request;

import javax.validation.constraints.NotNull;

public class CreateByIdDto {

	@NotNull(message = "Id is a required field")
	Long id;

	public CreateByIdDto() {
		super();
	}

	public CreateByIdDto(Long id) {
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
