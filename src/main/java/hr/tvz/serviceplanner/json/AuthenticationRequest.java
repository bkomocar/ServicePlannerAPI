package hr.tvz.serviceplanner.json;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AuthenticationRequest implements Serializable {
	private static final long serialVersionUID = 6624726180748515507L;
	
	@NotNull(message = "The name can not be null")
	@Length(min = 5, max = 20, message = "Name length should be between {min} and {max}")
	private String username;
	
	@NotNull(message = "The password can not be null")
    @Length(min = 5, max = 30, message = "Password length should be between {min} and {max}")
	private String password;

	public AuthenticationRequest() {
		super();
	}

	public AuthenticationRequest(String username, String password) {
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
