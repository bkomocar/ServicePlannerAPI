package hr.tvz.serviceplanner.viewmodels.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import hr.tvz.serviceplanner.persistence.models.User;

public class RegisterViewModel implements Serializable {

	private static final long serialVersionUID = 14564546L;

	@NotBlank
    @Length(min = 5, max = 20)
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
    @Length(min = 5, max = 30)
	private String password;
	
	public static User toUser(RegisterViewModel register) {
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
