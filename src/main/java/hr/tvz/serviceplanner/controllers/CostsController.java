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

import hr.tvz.serviceplanner.dtos.request.CreateCostDto;
import hr.tvz.serviceplanner.dtos.request.UpdateCostDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.services.interfaces.CostService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;

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
	public ResponseEntity<IdDto> createCost(@PathVariable("venueId") long id, @PathVariable("priceId") long priceId,
			@Valid @RequestBody CreateCostDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdDto costId = costService.createCost(priceId, model);
			if (costId != null) {
				return new ResponseEntity<IdDto>(costId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdDto>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/costs/{costId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateCost(@PathVariable("venueId") long id, @PathVariable("costId") long costId,
			@Valid @RequestBody UpdateCostDto model) {
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
