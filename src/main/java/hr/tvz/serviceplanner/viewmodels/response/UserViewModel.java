package hr.tvz.serviceplanner.viewmodels.response;

import hr.tvz.serviceplanner.persistence.models.User;

public class UserViewModel {

	private Long id;
	private String name;
	private String email;
	
	public static UserViewModel fromUser (User user) {
		
		if(user != null){
			return new UserViewModel(user.getId(), user.getUsername(), user.getEmail());
		}
		
		return null;
	}

	public UserViewModel(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
}
