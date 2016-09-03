package hr.tvz.serviceplanner.viewmodels.response;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.viewmodels.CustomerViewModel;

public class CustomerViewModelExtended extends CustomerViewModel {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String comment;

	public CustomerViewModelExtended() {
		super();
	}

	public CustomerViewModelExtended(Long id, String firstName, String lastName, String email, String phone, String comment) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.comment = comment;
	}

	public static CustomerViewModelExtended fromCustomer(Customer customer) {
		if (customer != null) {
			return new CustomerViewModelExtended(customer.getId(), customer.getFirstName(), customer.getLastName(),
					customer.getEmail(), customer.getPhone(), customer.getComment());
		}
		return null;
	}

	public static List<CustomerViewModelExtended> fromCustomer(List<Customer> customers) {
		if (customers != null) {
			return customers.stream().map(u -> CustomerViewModelExtended.fromCustomer(u)).collect(Collectors.toList());
		}
		return null;
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
