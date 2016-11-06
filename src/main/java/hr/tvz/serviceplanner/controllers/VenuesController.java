
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

import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.persistence.services.interfaces.VenueService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.GroupViewModel;
import hr.tvz.serviceplanner.viewmodels.VenueViewModel;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;
import hr.tvz.serviceplanner.viewmodels.request.CreateByNameViewModel;
import hr.tvz.serviceplanner.viewmodels.request.CreateVenueViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateVenueViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;
import hr.tvz.serviceplanner.viewmodels.response.VenueViewModelLarge;
import hr.tvz.serviceplanner.viewmodels.response.VenueViewModelSmall;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/venues")
public class VenuesController {

	@Autowired
	private VenueService venueService;

	@Autowired
	private UserRightsCheckerService userRightsCheckerService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@ApiOperation(value = "create a new venue", response = IdViewModel.class, notes = "Creates a Venue based on the supplied informations")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<VenueViewModel> createVenue(@Valid @RequestBody CreateVenueViewModel createModel) {
		VenueViewModel model = venueService.saveVenue(createModel, authenticationFacade.getUserId());
		if (model == null) {
			return new ResponseEntity<VenueViewModel>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<VenueViewModel>(model, HttpStatus.CREATED);
	}

	@ApiOperation(value = "get a venue by id", notes = "Returns a Venue based on the supplied id, and an optional type")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = VenueViewModelLarge.class, message = "No type parameter provided"),
			@ApiResponse(code = 200, response = VenueViewModelSmall.class, message = "Type parameter small") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<VenueViewModel> getVenue(@PathVariable("id") long id,
			@RequestParam(name = "type", required = false) ViewModelType type) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			VenueViewModel model = venueService.getVenue(id, type);
			if (model != null) {
				return new ResponseEntity<VenueViewModel>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<VenueViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<VenueViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<VenueViewModel>> getVenuesForUser(
			@RequestParam(name = "type", required = false) ViewModelType type) {
		Long userId = authenticationFacade.getUserId();
		List<VenueViewModel> venues = venueService.getVenuesForUser(userId.longValue(), type);
		if (venues != null) {
			return new ResponseEntity<List<VenueViewModel>>(venues, HttpStatus.OK);
		}
		return new ResponseEntity<List<VenueViewModel>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}/users", method = RequestMethod.POST)
	public ResponseEntity<Void> addUserToVenue(@PathVariable("id") long id,
			@Valid @RequestBody CreateByNameViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (venueService.addUser(id, model)) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateVenue(@PathVariable("id") long id,
			@Valid @RequestBody UpdateVenueViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (venueService.updateVenue(id, model) != false) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteVenue(@PathVariable("id") long id) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			venueService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/{venueId}/users/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeUser(@PathVariable("venueId") long venueId, @PathVariable("userId") long id) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, venueId)) {
			venueService.removeUser(venueId, id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/{venueId}/{groupName}", method = RequestMethod.GET)
	public ResponseEntity<GroupViewModel> getGroup(@PathVariable("venueId") long id,
			@PathVariable("groupName") String groupName,
			@RequestParam(name = "type", required = false) ViewModelType type) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			GroupViewModel model = venueService.getGroup(id, groupName, type);
			if (model != null) {
				return new ResponseEntity<GroupViewModel>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<GroupViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<GroupViewModel>(HttpStatus.FORBIDDEN);
		}
	}

}
