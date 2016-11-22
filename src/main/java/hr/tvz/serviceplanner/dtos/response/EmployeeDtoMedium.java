package hr.tvz.serviceplanner.dtos.response;

import hr.tvz.serviceplanner.dtos.EmployeeDto;

public class EmployeeDtoMedium implements EmployeeDto {

	private Long id;
	private String username;
	private String color;

	public EmployeeDtoMedium() {
		super();
	}

	public EmployeeDtoMedium(Long id, String username, String color) {
		super();
		this.id = id;
		this.username = username;
		this.color = color;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
