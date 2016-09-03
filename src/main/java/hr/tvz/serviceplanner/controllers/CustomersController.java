package hr.tvz.serviceplanner.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.serviceplanner.persistence.services.interfaces.CustomerService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.request.CreateCustomerViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateCustomerViewModel;
import hr.tvz.serviceplanner.viewmodels.response.CustomerViewModelLarge;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

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
	public ResponseEntity<IdViewModel> createCustomer(@PathVariable("venueId") long id,
			@Valid @RequestBody CreateCustomerViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdViewModel customerId = customerService.createCustomer(id, model);
			if (customerId != null) {
				return new ResponseEntity<IdViewModel>(customerId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateCustomer(@PathVariable("venueId") long id,
			@PathVariable("customerId") long customerId, @Valid @RequestBody UpdateCustomerViewModel model) {
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

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<CustomerViewModelLarge> getCustomer(@PathVariable("venueId") long id,
			@PathVariable("customerId") long customerId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			CustomerViewModelLarge model = customerService.getCustomer(customerId);
			if (model != null) {
				return new ResponseEntity<CustomerViewModelLarge>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<CustomerViewModelLarge>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<CustomerViewModelLarge>(HttpStatus.FORBIDDEN);
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
