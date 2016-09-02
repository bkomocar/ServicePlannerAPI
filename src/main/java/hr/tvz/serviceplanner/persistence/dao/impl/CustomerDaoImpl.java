package hr.tvz.serviceplanner.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.CustomerDao;
import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.persistence.models.Venue;

@Repository
public class CustomerDaoImpl extends AbstractHibernateDao<Customer> implements CustomerDao {

	public CustomerDaoImpl() {
		super();
		setClazz(Customer.class);
	}

	@Override
	public Long createCustomer(Long venueId, Customer customer) {
		Venue venue = getCurrentSession().get(Venue.class, venueId);
		if (venue != null) {
			customer.setVenue(venue);
			create(customer);
			return customer.getId();
		}
		return null;
	}

	@Override
	public boolean updateCustomer(Long customerId, Customer customer) {
		Customer originalCustomer = findOne(customerId);
		if (originalCustomer != null) {
			if (customer.getFirstName() != null) {
				originalCustomer.setFirstName(customer.getFirstName());
			}
			if (customer.getLastName() != null) {
				originalCustomer.setLastName(customer.getLastName());
			}
			if (customer.getEmail() != null) {
				originalCustomer.setEmail(customer.getEmail());
			}
			if (customer.getPhone() != null) {
				originalCustomer.setPhone(customer.getPhone());
			}
			if (customer.getComment() != null) {
				originalCustomer.setComment(customer.getComment());
			}
			update(originalCustomer);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(Long venueId, Long customerId) {
		Customer customer = findOne(customerId);
		if (customer.getVenue().getId() == venueId) {
			deleteById(customerId);
			return true;
		}
		return false;
	}

	@Override
	public Customer getCustomer(Long customerId) {
		return findOne(customerId);
	}

}
