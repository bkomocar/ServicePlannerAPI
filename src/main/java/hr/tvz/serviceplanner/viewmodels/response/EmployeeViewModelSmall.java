package hr.tvz.serviceplanner.viewmodels.response;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Employee;

public class EmployeeViewModelSmall {

	private Long id;
	private String username;
	private String color;

	public EmployeeViewModelSmall() {
		super();
	}

	public EmployeeViewModelSmall(Long id, String username, String color) {
		super();
		this.id = id;
		this.username = username;
		this.color = color;
	}

	public static EmployeeViewModelSmall fromEmployee(Employee employee) {
		if (employee != null) {
			return new EmployeeViewModelSmall(employee.getId(), employee.getUsername(), employee.getColor());
		}
		return null;
	}

	public static List<EmployeeViewModelSmall> fromEmployee(List<Employee> employees) {
		if (employees != null) {
			return employees.stream().map(v -> EmployeeViewModelSmall.fromEmployee(v)).collect(Collectors.toList());
		}
		return null;
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
