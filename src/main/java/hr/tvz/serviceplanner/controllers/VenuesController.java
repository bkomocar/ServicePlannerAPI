package hr.tvz.serviceplanner.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.persistence.services.interfaces.VenueService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.request.CreateByNameViewModel;
import hr.tvz.serviceplanner.viewmodels.request.CreateVenueViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateVenueViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;
import hr.tvz.serviceplanner.viewmodels.response.VenueViewModel;

@RestController
@RequestMapping("/venues")
public class VenuesController {

	@Autowired
	private VenueService venueService;

	@Autowired
	private UserRightsCheckerService userRightsCheckerService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<IdViewModel> createVenue(@Valid @RequestBody CreateVenueViewModel model,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<IdViewModel>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		IdViewModel idViewModel = venueService.saveVenue(model, authenticationFacade.getUserId());
		if (idViewModel == null) {
			return new ResponseEntity<IdViewModel>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<IdViewModel>(idViewModel, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<VenueViewModel> getVenue(@PathVariable("id") long id) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			Venue venue = venueService.findOne(id);
			if (venue != null) {
				return new ResponseEntity<VenueViewModel>(VenueViewModel.fromVenue(venue), HttpStatus.OK);
			} else {
				return new ResponseEntity<VenueViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<VenueViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<VenueViewModel>> getVenuesForUser() {
		Long userId = authenticationFacade.getUserId();
		List<Venue> venues = venueService.getVenuesForUser(userId.longValue());
		if (venues != null) {
			return new ResponseEntity<List<VenueViewModel>>(VenueViewModel.fromVenue(venues), HttpStatus.OK);
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

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeUser(@PathVariable("venueId") long venueId, @PathVariable("employeeId") long id) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, venueId)) {
			venueService.removeUser(venueId, id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

}
