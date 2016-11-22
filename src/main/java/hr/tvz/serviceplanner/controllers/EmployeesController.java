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

import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.EmployeeDto;
import hr.tvz.serviceplanner.dtos.request.CreateEmployeeDto;
import hr.tvz.serviceplanner.dtos.request.UpdateEmployeeDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.services.interfaces.EmployeeService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;

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
	public ResponseEntity<IdDto> createEmployee(@PathVariable("venueId") long id,
			@Valid @RequestBody CreateEmployeeDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdDto employeeId = employeeService.createEmployee(id, model);
			if (employeeId != null) {
				return new ResponseEntity<IdDto>(employeeId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdDto>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateEmployee(@PathVariable("venueId") long id,
			@PathVariable("employeeId") long employeeId, @Valid @RequestBody UpdateEmployeeDto model) {
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
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("venueId") long id,
			@PathVariable("employeeId") long employeeId, @RequestParam(name = "type", required = false) DtoType type) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			EmployeeDto model = employeeService.getEmployee(employeeId, type);
			if (model != null) {
				return new ResponseEntity<EmployeeDto>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<EmployeeDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<EmployeeDto>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeDto>> getEmployees(@PathVariable("venueId") long id,
			@RequestParam(name = "type", required = false) DtoType type) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			List<EmployeeDto> models = employeeService.getEmployeesForVenue(id, type);
			if (models != null) {
				return new ResponseEntity<List<EmployeeDto>>(models, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<EmployeeDto>>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<List<EmployeeDto>>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable("venueId") long id,
			@PathVariable("employeeId") long employeeId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			employeeService.deleteEmployee(id, employeeId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}
}
