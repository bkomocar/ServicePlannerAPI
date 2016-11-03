package hr.tvz.serviceplanner.persistence.dao.interfaces;

import java.util.SortedSet;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.persistence.models.Employee;

public interface CustomerDao extends Operations<Customer> {

	public Long createCustomer(Long venueId, Customer customer);

	public boolean updateCustomer(Long customerId, Customer customer);

	public boolean deleteCustomer(Long venueId, Long customerId);
	
	public SortedSet<Customer> getCustomersForVenue(Long venueId);

	public Customer getCustomer(Long customerId);
}
