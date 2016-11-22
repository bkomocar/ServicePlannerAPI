package hr.tvz.serviceplanner.persistence.dao.impl;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
			customer.setDeleted(true);
			update(customer);
			return true;
		}
		return false;
	}

	@Override
	public Customer getCustomer(Long customerId) {
		Customer c = findOne(customerId);
		if (c != null && !c.isDeleted()) {
			return c;
		}
		return null;
	}

	@Override
	public SortedSet<Customer> getCustomersForVenue(Long venueId) {
		Venue venue = getCurrentSession().get(Venue.class, venueId);
		if (venue != null) {
			Criteria crit = getCurrentSession().createCriteria(Customer.class);
			crit.add(Restrictions.eq("deleted", false));
			crit.add(Restrictions.eq("venue", venue));
			List<Customer> results = crit.list();
			if (results != null) {
				SortedSet<Customer> list = new TreeSet<Customer>(results);
				return list;
			}
		}
		return null;
	}

}
