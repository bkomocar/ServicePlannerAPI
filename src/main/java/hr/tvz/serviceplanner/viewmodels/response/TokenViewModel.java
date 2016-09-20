package hr.tvz.serviceplanner.viewmodels.response;

import java.io.Serializable;

public class TokenViewModel implements Serializable {
	
	private static final long serialVersionUID = -6624726180748515507L;
	private String token;

	public TokenViewModel() {
		super();
	}

	public TokenViewModel(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
