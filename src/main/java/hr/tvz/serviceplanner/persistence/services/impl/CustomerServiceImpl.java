package hr.tvz.serviceplanner.persistence.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.CustomerDao;
import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.CustomerService;
import hr.tvz.serviceplanner.viewmodels.CustomerViewModel;
import hr.tvz.serviceplanner.viewmodels.CustomerViewModelFactory;
import hr.tvz.serviceplanner.viewmodels.EmployeeViewModelFactory;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;
import hr.tvz.serviceplanner.viewmodels.request.CreateCustomerViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateCustomerViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@Service
public class CustomerServiceImpl extends AbstractService<Customer> implements CustomerService {

	@Autowired
	private CustomerDao dao;

	@Override
	public IdViewModel createCustomer(Long id, CreateCustomerViewModel model) {
		Long customerId = dao.createCustomer(id, CreateCustomerViewModel.toCustomer(model));
		if (customerId != null) {
			return new IdViewModel(customerId);
		}
		return null;
	}

	@Override
	public boolean updateCustomer(Long id, UpdateCustomerViewModel model) {
		if (model != null) {
			return dao.updateCustomer(id, UpdateCustomerViewModel.toCustomer(model));
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(Long venueId, Long customerId) {
		return dao.deleteCustomer(venueId, customerId);
	}

	@Override
	public CustomerViewModel getCustomer(Long customerId) {
		Customer customer = dao.getCustomer(customerId);
		if (customer != null) {
			return CustomerViewModelFactory.toCustomerViewModel(customer, ViewModelType.large);
		}
		return null;
	}

	@Override
	protected Operations<Customer> getDao() {
		return dao;
	}

	@Override
	public List<CustomerViewModel> getCustomersForVenue(Long venueId, ViewModelType type) {
		SortedSet<Customer> customers = dao.getCustomersForVenue(venueId);
		if (customers != null) {
			return CustomerViewModelFactory.toCustomerViewModel(new ArrayList<Customer>(customers), type);
		}
		return null;
	}

}
