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

import hr.tvz.serviceplanner.persistence.services.interfaces.CostService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.request.CreateCostViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateCostViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@RestController
@RequestMapping("venues/{venueId}")
public class CostsController {
	@Autowired
	private UserRightsCheckerService userRightsCheckerService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private CostService costService;

	@RequestMapping(value = "/prices/{priceId}/costs", method = RequestMethod.POST)
	public ResponseEntity<IdViewModel> createCost(@PathVariable("venueId") long id,
			@PathVariable("priceId") long priceId, @Valid @RequestBody CreateCostViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdViewModel costId = costService.createCost(priceId, model);
			if (costId != null) {
				return new ResponseEntity<IdViewModel>(costId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/costs/{costId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateCost(@PathVariable("venueId") long id, @PathVariable("costId") long costId,
			@Valid @RequestBody UpdateCostViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (costService.updateCost(costId, model) != false) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/costs/{costId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCost(@PathVariable("venueId") long id, @PathVariable("costId") long costId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (costService.deleteCost(costId)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);

	}
}
