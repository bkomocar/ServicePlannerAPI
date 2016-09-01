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

import hr.tvz.serviceplanner.persistence.services.interfaces.PriceService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.request.CreatePriceViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdatePriceViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@RestController
@RequestMapping("venues/{venueId}")
public class PricesController {
	@Autowired
	private UserRightsCheckerService userRightsCheckerService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private PriceService priceService;

	@RequestMapping(value = "/products/{productId}/prices", method = RequestMethod.POST)
	public ResponseEntity<IdViewModel> createProduct(@PathVariable("venueId") long id,
			@PathVariable("productId") long productId, @Valid @RequestBody CreatePriceViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdViewModel priceId = priceService.createPrice(productId, model);
			if (priceId != null) {
				return new ResponseEntity<IdViewModel>(priceId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/prices/{priceId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProduct(@PathVariable("venueId") long id, @PathVariable("priceId") long priceId,
			@Valid @RequestBody UpdatePriceViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (priceService.updatePrice(priceId, model) != false) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/prices/{priceId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable("venueId") long id, @PathVariable("priceId") long priceId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (priceService.deletePrice(priceId)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}
}
