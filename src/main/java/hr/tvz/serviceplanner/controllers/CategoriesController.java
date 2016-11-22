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

import hr.tvz.serviceplanner.dtos.CategoryDto;
import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.EmployeeDto;
import hr.tvz.serviceplanner.dtos.request.CreateByIdDto;
import hr.tvz.serviceplanner.dtos.request.CreateCategoryDto;
import hr.tvz.serviceplanner.dtos.request.UpdateCategoryDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.services.interfaces.CategoryService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;

@RestController
@RequestMapping("venues/{venueId}")
public class CategoriesController {

	@Autowired
	private UserRightsCheckerService userRightsCheckerService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/groups/{groupId}/categories", method = RequestMethod.POST)
	public ResponseEntity<IdDto> createCategory(@PathVariable("venueId") long id, @PathVariable("groupId") long groupId,
			@Valid @RequestBody CreateCategoryDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdDto categoryId = categoryService.createCategory(groupId, model);
			if (categoryId != null) {
				return new ResponseEntity<IdDto>(categoryId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdDto>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateCategory(@PathVariable("venueId") long id,
			@PathVariable("categoryId") long categoryId, @Valid @RequestBody UpdateCategoryDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (categoryService.updateCategory(categoryId, model) != false) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("venueId") long id,
			@PathVariable("categoryId") long categoryId, @RequestParam(name = "type", required = false) DtoType type) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			CategoryDto model = categoryService.getCategory(categoryId, type);
			if (model != null) {
				return new ResponseEntity<CategoryDto>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<CategoryDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<CategoryDto>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCategory(@PathVariable("venueId") long id,
			@PathVariable("categoryId") long categoryId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			categoryService.deleteById(categoryId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/categories/{categoryId}/employees/{employeeId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeEmployee(@PathVariable("venueId") long id,
			@PathVariable("categoryId") long categoryId, @PathVariable("employeeId") long employeeId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			categoryService.removeEmployee(categoryId, employeeId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/categories/{categoryId}/employees", method = RequestMethod.POST)
	public ResponseEntity<Void> addEmployee(@PathVariable("venueId") long id,
			@PathVariable("categoryId") long categoryId, @Valid @RequestBody CreateByIdDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (categoryService.addEmployee(categoryId, model)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/categories/{categoryId}/employees", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeDto>> getEmployees(@PathVariable("venueId") long id,
			@PathVariable("categoryId") long categoryId, @RequestParam(name = "type", required = false) DtoType type) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			List<EmployeeDto> models = categoryService.getEmployees(id, categoryId, type);
			if (models != null) {
				return new ResponseEntity<List<EmployeeDto>>(models, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<EmployeeDto>>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<List<EmployeeDto>>(HttpStatus.FORBIDDEN);
		}
	}
}
