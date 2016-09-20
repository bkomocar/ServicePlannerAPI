package hr.tvz.serviceplanner.viewmodels.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.persistence.models.User;

public class RegisterViewModel implements Serializable {

	private static final long serialVersionUID = 14564546L;

	@NotNull(message = "The name can not be null")
    @Length(min = 5, max = 20, message = "Name length should be between {min} and {max}")
	private String username;
	
	@NotNull(message = "The email can not be null")
	@Email(message = "The email has to be a valid email adress.")
	private String email;
	
	@NotNull(message = "The password can not be null")
    @Length(min = 5, max = 30, message = "Password length should be between {min} and {max}")
	private String password;
	
	public static User toUser(RegisterViewModel register) {
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
