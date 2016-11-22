package hr.tvz.serviceplanner.dtos;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.dtos.response.CustomerDtoSmall;
import hr.tvz.serviceplanner.dtos.response.CustomerDtoExtended;
import hr.tvz.serviceplanner.dtos.response.CustomerDtoLarge;
import hr.tvz.serviceplanner.persistence.models.Customer;

public class CustomerDtoFactory {
	public static CustomerDto toCustomerDto(Customer customer, DtoType type) {
		if (customer != null) {
			if (type == null || type == DtoType.large || type == DtoType.medium) {
				return new CustomerDtoLarge(customer.getId(), customer.getFirstName(), customer.getLastName(),
						customer.getEmail(), customer.getPhone(), customer.getComment());
			} else if (type == DtoType.extended) {
				return new CustomerDtoExtended(customer.getId(), customer.getFirstName(), customer.getLastName(),
						customer.getEmail(), customer.getPhone(), customer.getComment());
			} else {
				return new CustomerDtoSmall(customer.getId(), customer.getFirstName(), customer.getLastName());
			}
		}
		return null;
	}

	public static List<CustomerDto> toCustomerDto(List<Customer> customers, DtoType type) {
		if (customers != null) {
			return customers.stream().map(v -> CustomerDtoFactory.toCustomerDto(v, type)).collect(Collectors.toList());
		}
		return null;
	}
}
