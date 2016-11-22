package hr.tvz.serviceplanner.persistence.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.dtos.CustomerDto;
import hr.tvz.serviceplanner.dtos.CustomerDtoFactory;
import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.request.CreateCustomerDto;
import hr.tvz.serviceplanner.dtos.request.UpdateCustomerDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.CustomerDao;
import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.CustomerService;

@Service
public class CustomerServiceImpl extends AbstractService<Customer> implements CustomerService {

	@Autowired
	private CustomerDao dao;

	@Override
	public IdDto createCustomer(Long id, CreateCustomerDto model) {
		Long customerId = dao.createCustomer(id, CreateCustomerDto.toCustomer(model));
		if (customerId != null) {
			return new IdDto(customerId);
		}
		return null;
	}

	@Override
	public boolean updateCustomer(Long id, UpdateCustomerDto model) {
		if (model != null) {
			return dao.updateCustomer(id, UpdateCustomerDto.toCustomer(model));
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(Long venueId, Long customerId) {
		return dao.deleteCustomer(venueId, customerId);
	}

	@Override
	public CustomerDto getCustomer(Long customerId) {
		Customer customer = dao.getCustomer(customerId);
		if (customer != null) {
			return CustomerDtoFactory.toCustomerDto(customer, DtoType.large);
		}
		return null;
	}

	@Override
	protected Operations<Customer> getDao() {
		return dao;
	}

	@Override
	public List<CustomerDto> getCustomersForVenue(Long venueId, DtoType type) {
		SortedSet<Customer> customers = dao.getCustomersForVenue(venueId);
		if (customers != null) {
			return CustomerDtoFactory.toCustomerDto(new ArrayList<Customer>(customers), type);
		}
		return null;
	}

}
