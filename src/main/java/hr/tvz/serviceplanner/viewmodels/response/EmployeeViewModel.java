package hr.tvz.serviceplanner.viewmodels.response;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Employee;

public class EmployeeViewModel {

	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String color;
	private String email;
	private String phone;
	private String comment;

	public EmployeeViewModel() {
		super();
	}

	public EmployeeViewModel(Long id, String username, String firstName, String lastName, String color, String email,
			String phone, String comment) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.color = color;
		this.email = email;
		this.phone = phone;
		this.comment = comment;
	}

	public static EmployeeViewModel fromEmployee(Employee employee) {
		if (employee != null) {
			return new EmployeeViewModel(employee.getId(), employee.getUsername(), employee.getFirstName(),
					employee.getLastName(), employee.getColor(), employee.getEmail(), employee.getPhone(),
					employee.getComment());
		}
		return null;
	}

	public static List<EmployeeViewModel> fromEmployee(List<Employee> employees) {
		if (employees != null) {
			return employees.stream().map(v -> EmployeeViewModel.fromEmployee(v)).collect(Collectors.toList());
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
