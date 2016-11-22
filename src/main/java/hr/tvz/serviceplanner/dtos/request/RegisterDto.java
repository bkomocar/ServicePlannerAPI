package hr.tvz.serviceplanner.dtos.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.persistence.models.User;

public class RegisterDto implements Serializable {

	private static final long serialVersionUID = 14564546L;

	@NotNull(message = "Name is a required field")
	@Length(min = 5, max = 20, message = "Name length should be between {min} and {max}")
	private String username;

	@NotNull(message = "Email is a required field")
	@Email(message = "Email has to be a valid email adress.")
	@Length(max = 255, message = "Email can not be longer than {max} characters")
	private String email;

	@NotNull(message = "Password is a required field")
	@Length(min = 5, max = 30, message = "Password length should be between {min} and {max}")
	private String password;

	public static User toUser(RegisterDto register) {
		if (register != null) {
			return new User(register.email, register.username, register.password);
		}
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
