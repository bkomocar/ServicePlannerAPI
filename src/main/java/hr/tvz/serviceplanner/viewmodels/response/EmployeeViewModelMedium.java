package hr.tvz.serviceplanner.viewmodels.response;

import hr.tvz.serviceplanner.viewmodels.EmployeeViewModel;

public class EmployeeViewModelMedium extends EmployeeViewModel {

	private Long id;
	private String username;
	private String color;

	public EmployeeViewModelMedium() {
		super();
	}

	public EmployeeViewModelMedium(Long id, String username, String color) {
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
