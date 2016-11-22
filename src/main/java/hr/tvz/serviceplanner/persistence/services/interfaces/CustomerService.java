package hr.tvz.serviceplanner.persistence.services.interfaces;

import java.util.List;

import hr.tvz.serviceplanner.dtos.CustomerDto;
import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.request.CreateCustomerDto;
import hr.tvz.serviceplanner.dtos.request.UpdateCustomerDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Customer;

public interface CustomerService extends Operations<Customer> {

	public IdDto createCustomer(Long id, CreateCustomerDto model);

	public boolean updateCustomer(Long id, UpdateCustomerDto model);

	public boolean deleteCustomer(Long venueId, Long customerId);

	public CustomerDto getCustomer(Long customerId);

	public List<CustomerDto> getCustomersForVenue(Long venueId, DtoType type);
}
