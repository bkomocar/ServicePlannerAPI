package hr.tvz.serviceplanner.viewmodels.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.persistence.models.User;

public class UpdateUserViewModel implements Serializable {

	private static final long serialVersionUID = 14564546L;

	@Length(min = 5, max = 20, message = "Name length should be between {min} and {max} characters")
	private String name;

	@Email(message = "Email has to be a valid email adress.")
	@Length(max = 255, message = "Email can not be longer than {max} characters")
	private String email;

	@Length(min = 5, max = 30, message = "Password length should be between {min} and {max} characters")
	private String password;

	public static User toUser(UpdateUserViewModel register) {
		if (register != null) {
			return new User(register.email, register.name, register.password);
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
