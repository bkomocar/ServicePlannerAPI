package hr.tvz.serviceplanner.viewmodels.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.persistence.models.Customer;

public class CreateCustomerViewModel {

	@NotNull
	@Length(max = 50)
	private String firstName;

	@NotNull
	@Length(max = 50)
	private String lastName;

	@Length(max = 255)
	private String email;

	@Length(max = 20)
	private String phone;

	@Length(max = 500)
	private String comment;

	public CreateCustomerViewModel() {
		super();
	}

	public CreateCustomerViewModel(String firstName, String lastName, String email, String phone, String comment) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.comment = comment;
	}

	public static Customer toCustomer(CreateCustomerViewModel model) {
		if (model != null) {
			return new Customer(model.firstName, model.lastName, model.email, model.phone, model.comment);
		}
		return null;
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
