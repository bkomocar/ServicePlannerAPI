package hr.tvz.serviceplanner.viewmodels;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.viewmodels.response.CustomerViewModelSmall;
import hr.tvz.serviceplanner.viewmodels.response.CustomerViewModelExtended;
import hr.tvz.serviceplanner.viewmodels.response.CustomerViewModelLarge;

public class CustomerViewModelFactory {
	public static CustomerViewModel toCustomerViewModel(Customer customer, ViewModelType type) {
		if (customer != null) {
			if (type == null || type == ViewModelType.large || type == ViewModelType.medium) {
				return new CustomerViewModelLarge(customer.getId(), customer.getFirstName(), customer.getLastName(),
						customer.getEmail(), customer.getPhone(), customer.getComment());
			} else if (type == ViewModelType.extended) {
				return new CustomerViewModelExtended(customer.getId(), customer.getFirstName(), customer.getLastName(),
						customer.getEmail(), customer.getPhone(), customer.getComment());
			} else {
				return new CustomerViewModelSmall(customer.getId(), customer.getFirstName(), customer.getLastName());
			}
		}
		return null;
	}

	public static List<CustomerViewModel> toCustomerViewModel(List<Customer> customers, ViewModelType type) {
		if (customers != null) {
			return customers.stream().map(v -> CustomerViewModelFactory.toCustomerViewModel(v, type))
					.collect(Collectors.toList());
		}
		return null;
	}
}
