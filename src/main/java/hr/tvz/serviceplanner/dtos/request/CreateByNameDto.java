package hr.tvz.serviceplanner.dtos.request;

import org.hibernate.validator.constraints.NotBlank;

public class CreateByNameDto {

	@NotBlank(message = "Name field can not be empty")
	private String name;

	public CreateByNameDto() {
		super();
	}

	public CreateByNameDto(String name) {
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
