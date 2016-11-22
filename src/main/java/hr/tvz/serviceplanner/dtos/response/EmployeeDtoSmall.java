package hr.tvz.serviceplanner.dtos.response;

import hr.tvz.serviceplanner.dtos.EmployeeDto;

public class EmployeeDtoSmall implements EmployeeDto {

	private Long id;
	private String username;

	public EmployeeDtoSmall() {
		super();
	}

	public EmployeeDtoSmall(Long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
