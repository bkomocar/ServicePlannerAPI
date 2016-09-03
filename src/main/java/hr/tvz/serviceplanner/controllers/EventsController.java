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

import hr.tvz.serviceplanner.persistence.services.interfaces.EventService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.request.CreateByIdViewModel;
import hr.tvz.serviceplanner.viewmodels.request.CreateEventViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateEventViewModel;
import hr.tvz.serviceplanner.viewmodels.response.EventViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@RestController
@RequestMapping("venues/{venueId}/events")
public class EventsController {
	@Autowired
	private UserRightsCheckerService userRightsCheckerService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private EventService eventService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<IdViewModel> createEvent(@PathVariable("venueId") long id,
			@Valid @RequestBody CreateEventViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdViewModel eventId = eventService.createEvent(id, model);
			if (eventId != null) {
				return new ResponseEntity<IdViewModel>(eventId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{eventId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateEvent(@PathVariable("venueId") long id, @PathVariable("eventId") long eventId,
			@Valid @RequestBody UpdateEventViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (eventService.updateEvent(eventId, model) != false) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
	public ResponseEntity<EventViewModel> getEvent(@PathVariable("venueId") long id,
			@PathVariable("eventId") long eventId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			EventViewModel model = eventService.getEvent(eventId);
			if (model != null) {
				return new ResponseEntity<EventViewModel>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<EventViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<EventViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEvent(@PathVariable("venueId") long id, @PathVariable("eventId") long eventId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			eventService.deleteEvent(id, eventId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{eventId}/purchases/{purchaseId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removePurchase(@PathVariable("venueId") long id, @PathVariable("eventId") long eventId,
			@PathVariable("purchaseId") long purchaseId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			eventService.removePurchase(eventId, purchaseId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{eventId}/purchases", method = RequestMethod.POST)
	public ResponseEntity<Void> addPurchase(@PathVariable("venueId") long id, @PathVariable("eventId") long eventId,
			@Valid @RequestBody CreateByIdViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (eventService.addPurchase(eventId, model)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}
}
