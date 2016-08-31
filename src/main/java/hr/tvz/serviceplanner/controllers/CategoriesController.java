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

import hr.tvz.serviceplanner.persistence.services.interfaces.CategoryService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.request.CreateCategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateCategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.response.CategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

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
	public ResponseEntity<IdViewModel> createCategory(@PathVariable("venueId") long id,
			@PathVariable("groupId") long groupId, @Valid @RequestBody CreateCategoryViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdViewModel categoryId = categoryService.createCategory(groupId, model);
			if (categoryId != null) {
				return new ResponseEntity<IdViewModel>(categoryId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateCategory(@PathVariable("venueId") long id,
			@PathVariable("categoryId") long categoryId, @Valid @RequestBody UpdateCategoryViewModel model) {
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
	public ResponseEntity<CategoryViewModel> getCategory(@PathVariable("venueId") long id,
			@PathVariable("categoryId") long categoryId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			CategoryViewModel model = categoryService.getCategory(categoryId);
			if (model != null) {
				return new ResponseEntity<CategoryViewModel>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<CategoryViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<CategoryViewModel>(HttpStatus.FORBIDDEN);
		}
	}

}