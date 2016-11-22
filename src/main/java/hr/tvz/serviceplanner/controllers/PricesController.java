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

import hr.tvz.serviceplanner.dtos.request.CreatePriceDto;
import hr.tvz.serviceplanner.dtos.request.UpdatePriceDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.services.interfaces.PriceService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;

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
	public ResponseEntity<IdDto> createProduct(@PathVariable("venueId") long id,
			@PathVariable("productId") long productId, @Valid @RequestBody CreatePriceDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdDto priceId = priceService.createPrice(productId, model);
			if (priceId != null) {
				return new ResponseEntity<IdDto>(priceId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdDto>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/prices/{priceId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProduct(@PathVariable("venueId") long id, @PathVariable("priceId") long priceId,
			@Valid @RequestBody UpdatePriceDto model) {
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
