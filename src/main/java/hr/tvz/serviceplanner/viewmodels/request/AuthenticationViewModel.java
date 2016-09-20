package hr.tvz.serviceplanner.viewmodels.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AuthenticationViewModel implements Serializable {
	private static final long serialVersionUID = 6624726180748515507L;
	
	@NotNull(message = "Name is a required field")
	@Length(min = 5, max = 20, message = "Name length should be between {min} and {max} characters")
	private String username;
	
	@NotNull(message = "Password is a required field")
    @Length(min = 5, max = 30, message = "Password length should be between {min} and {max} characters")
	private String password;

	public AuthenticationViewModel() {
		super();
	}

	public AuthenticationViewModel(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
