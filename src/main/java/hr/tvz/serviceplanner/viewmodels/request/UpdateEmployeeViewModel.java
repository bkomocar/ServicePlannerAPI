package hr.tvz.serviceplanner.viewmodels.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.persistence.models.Employee;

public class UpdateEmployeeViewModel {

	@Length(min = 4, max = 20, message = "Username length should be between {min} and {max} characters")
	private String username;

	@Length(min = 1, max = 50, message = "First Name length should be between {min} and {max} characters")
	private String firstName;

	@Length(min = 1, max = 50, message = "Last Name length should be between {min} and {max} characters")
	private String lastName;

	@Length(max = 20, message = "Color can not be longer than {max} characters")
	private String color;

	@Email(message = "Email has to be a valid email adress.")
	@Length(max = 255, message = "Email can not be longer than {max} characters")
	private String email;

	@Length(max = 20, message = "Phone can not be longer than {max} characters")
	private String phone;

	@Length(max = 500, message = "Comment can not be longer than {max} characters")
	private String comment;

	public UpdateEmployeeViewModel() {
		super();
	}

	public UpdateEmployeeViewModel(String username, String firstName, String lastName, String color, String email,
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

	public static Employee toEmployee(UpdateEmployeeViewModel model) {
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
