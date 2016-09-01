package hr.tvz.serviceplanner.viewmodels.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.persistence.models.Employee;

public class CreateEmployeeViewModel {

	@NotNull
	@Length(min = 4, max = 20)
	private String username;

	@NotNull
	@Length(max = 50)
	private String firstName;

	@NotNull
	@Length(max = 50)
	private String lastName;

	@Length(max = 20)
	private String color;

	@Length(max = 255)
	@Email
	private String email;

	@Length(max = 20)
	private String phone;

	@Length(max = 500)
	private String comment;

	public CreateEmployeeViewModel() {
		super();
	}

	public CreateEmployeeViewModel(String username, String firstName, String lastName, String color, String email,
			String phone, String comment) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.color = color;
		this.email = email;
		this.phone = phone;
		this.comment = comment;
	}

	public static Employee toEmployee(CreateEmployeeViewModel model) {
		if (model != null) {
			return new Employee(model.username, model.firstName, model.lastName, model.color, model.email, model.phone,
					model.comment);
		}
		return null;
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
