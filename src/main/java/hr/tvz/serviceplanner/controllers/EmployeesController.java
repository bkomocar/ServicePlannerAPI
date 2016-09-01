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

import hr.tvz.serviceplanner.persistence.services.interfaces.EmployeeService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.request.CreateEmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateEmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.response.EmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@RestController
@RequestMapping("venues/{venueId}/employees")
public class EmployeesController {
	@Autowired
	private UserRightsCheckerService userRightsCheckerService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<IdViewModel> createEmployee(@PathVariable("venueId") long id,
			@Valid @RequestBody CreateEmployeeViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdViewModel employeeId = employeeService.createEmployee(id, model);
			if (employeeId != null) {
				return new ResponseEntity<IdViewModel>(employeeId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateGroup(@PathVariable("venueId") long id,
			@PathVariable("employeeId") long employeeId, @Valid @RequestBody UpdateEmployeeViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (employeeService.updateEmployee(employeeId, model) != false) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeViewModel> getGroup(@PathVariable("venueId") long id,
			@PathVariable("employeeId") long employeeId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			EmployeeViewModel model = employeeService.getEmployee(employeeId);
			if (model != null) {
				return new ResponseEntity<EmployeeViewModel>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<EmployeeViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<EmployeeViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable("venueId") long id,
			@PathVariable("employeeId") long employeeId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			employeeService.deleteById(employeeId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}
}
