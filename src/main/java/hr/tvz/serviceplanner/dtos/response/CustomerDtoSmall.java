package hr.tvz.serviceplanner.dtos.response;

import hr.tvz.serviceplanner.dtos.CustomerDto;

public class CustomerDtoSmall implements CustomerDto {

	private Long id;
	private String firstName;
	private String lastName;

	public CustomerDtoSmall() {
		super();
	}

	public CustomerDtoSmall(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
