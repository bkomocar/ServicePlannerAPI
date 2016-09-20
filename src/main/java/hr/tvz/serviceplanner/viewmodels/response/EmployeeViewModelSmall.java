package hr.tvz.serviceplanner.viewmodels.response;

import hr.tvz.serviceplanner.viewmodels.EmployeeViewModel;

public class EmployeeViewModelSmall implements EmployeeViewModel {

	private Long id;
	private String username;

	public EmployeeViewModelSmall() {
		super();
	}

	public EmployeeViewModelSmall(Long id, String username) {
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
