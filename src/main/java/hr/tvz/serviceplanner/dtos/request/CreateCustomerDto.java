package hr.tvz.serviceplanner.dtos.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import hr.tvz.serviceplanner.persistence.models.Customer;

public class CreateCustomerDto {

	@NotBlank(message = "First name field can not be empty")
	@Length(max = 50, message = "First name can not be longer than {max} characters")
	private String firstName;

	@NotBlank(message = "Last name field can not be empty")
	@Length(max = 50, message = "Last name can not be longer than {max} characters")
	private String lastName;

	@Email(message = "Email has to be a valid email adress.")
	@Length(max = 255, message = "Email can not be longer than {max} characters")
	private String email;

	@Length(max = 20, message = "Phone can not be longer than {max} characters")
	private String phone;

	@Length(max = 500, message = "Comment can not be longer than {max} characters")
	private String comment;

	public CreateCustomerDto() {
		super();
	}

	public CreateCustomerDto(String firstName, String lastName, String email, String phone, String comment) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.comment = comment;
	}

	public static Customer toCustomer(CreateCustomerDto model) {
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
