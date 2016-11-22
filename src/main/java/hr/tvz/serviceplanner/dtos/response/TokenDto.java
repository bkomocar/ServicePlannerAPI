package hr.tvz.serviceplanner.dtos.response;

import java.io.Serializable;

public class TokenDto implements Serializable {

	private static final long serialVersionUID = -6624726180748515507L;
	private String token;

	public TokenDto() {
		super();
	}

	public TokenDto(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
