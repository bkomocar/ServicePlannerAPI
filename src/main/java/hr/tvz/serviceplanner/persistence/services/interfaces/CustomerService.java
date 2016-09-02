package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.viewmodels.request.CreateCustomerViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateCustomerViewModel;
import hr.tvz.serviceplanner.viewmodels.response.CustomerViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

public interface CustomerService extends Operations<Customer> {

	public IdViewModel createCustomer(Long id, CreateCustomerViewModel model);

	public boolean updateCustomer(Long id, UpdateCustomerViewModel model);

	public boolean deleteCustomer(Long venueId, Long customerId);

	public CustomerViewModel getCustomer(Long customerId);
}