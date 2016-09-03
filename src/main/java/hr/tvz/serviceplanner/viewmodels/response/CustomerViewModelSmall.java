package hr.tvz.serviceplanner.viewmodels.response;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.viewmodels.CustomerViewModel;

public class CustomerViewModelSmall extends CustomerViewModel {

	private Long id;
	private String firstName;
	private String lastName;

	public CustomerViewModelSmall() {
		super();
	}

	public CustomerViewModelSmall(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public static CustomerViewModelSmall fromCustomer(Customer customer) {
		if (customer != null) {
			return new CustomerViewModelSmall(customer.getId(), customer.getFirstName(), customer.getLastName());
		}
		return null;
	}

	public static List<CustomerViewModelSmall> fromCustomer(List<Customer> customers) {
		if (customers != null) {
			return customers.stream().map(u -> CustomerViewModelSmall.fromCustomer(u)).collect(Collectors.toList());
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

}
