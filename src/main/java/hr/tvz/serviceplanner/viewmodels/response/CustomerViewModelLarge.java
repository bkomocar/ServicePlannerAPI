package hr.tvz.serviceplanner.viewmodels.response;

import hr.tvz.serviceplanner.viewmodels.CustomerViewModel;

public class CustomerViewModelLarge implements CustomerViewModel {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String comment;

	public CustomerViewModelLarge() {
		super();
	}

	public CustomerViewModelLarge(Long id, String firstName, String lastName, String email, String phone,
			String comment) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
