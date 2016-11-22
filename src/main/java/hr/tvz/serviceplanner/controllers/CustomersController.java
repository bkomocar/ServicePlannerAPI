package hr.tvz.serviceplanner.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.serviceplanner.dtos.CustomerDto;
import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.request.CreateCustomerDto;
import hr.tvz.serviceplanner.dtos.request.UpdateCustomerDto;
import hr.tvz.serviceplanner.dtos.response.CustomerDtoLarge;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.services.interfaces.CustomerService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("venues/{venueId}/customers")
public class CustomersController {
	@Autowired
	private UserRightsCheckerService userRightsCheckerService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private CustomerService customerService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<IdDto> createCustomer(@PathVariable("venueId") long id,
			@Valid @RequestBody CreateCustomerDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdDto customerId = customerService.createCustomer(id, model);
			if (customerId != null) {
				return new ResponseEntity<IdDto>(customerId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdDto>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateCustomer(@PathVariable("venueId") long id,
			@PathVariable("customerId") long customerId, @Valid @RequestBody UpdateCustomerDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (customerService.updateCustomer(customerId, model) != false) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation(value = "get a customer by id", response = CustomerDtoLarge.class, notes = "Returns a customer based on the supplied id")
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable("venueId") long id,
			@PathVariable("customerId") long customerId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			CustomerDto model = customerService.getCustomer(customerId);
			if (model != null) {
				return new ResponseEntity<CustomerDto>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<CustomerDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<CustomerDto>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CustomerDto>> getCustomers(@PathVariable("venueId") long id,
			@RequestParam(name = "type", required = false) DtoType type) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			List<CustomerDto> models = customerService.getCustomersForVenue(id, type);
			if (models != null) {
				return new ResponseEntity<List<CustomerDto>>(models, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<CustomerDto>>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<List<CustomerDto>>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCustomer(@PathVariable("venueId") long id,
			@PathVariable("customerId") long customerId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			customerService.deleteCustomer(id, customerId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}
}
