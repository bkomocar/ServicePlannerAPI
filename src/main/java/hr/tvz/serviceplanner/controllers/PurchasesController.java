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

import hr.tvz.serviceplanner.persistence.services.interfaces.PurchaseService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.PurchaseViewModel;
import hr.tvz.serviceplanner.viewmodels.request.CreatePurchaseViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdatePurchaseViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@RestController
@RequestMapping("venues/{venueId}/purchases")
public class PurchasesController {

	@Autowired
	private UserRightsCheckerService userRightsCheckerService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private PurchaseService purchaseService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<IdViewModel> createPurchase(@PathVariable("venueId") long id,
			@Valid @RequestBody CreatePurchaseViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdViewModel productId = purchaseService.createPurchase(id, model);
			if (productId != null) {
				return new ResponseEntity<IdViewModel>(productId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{purchaseId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updatePurchase(@PathVariable("venueId") long id,
			@PathVariable("purchaseId") long purchaseId, @Valid @RequestBody UpdatePurchaseViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (purchaseService.updatePurchase(purchaseId, model) != false) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{purchaseId}", method = RequestMethod.GET)
	public ResponseEntity<PurchaseViewModel> getPurchase(@PathVariable("venueId") long id,
			@PathVariable("purchaseId") long purchaseId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			PurchaseViewModel model = purchaseService.getPurchase(purchaseId);
			if (model != null) {
				return new ResponseEntity<PurchaseViewModel>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<PurchaseViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<PurchaseViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{purchaseId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePurchase(@PathVariable("venueId") long id,
			@PathVariable("purchaseId") long purchaseId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			purchaseService.deletePurchase(id, purchaseId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}
}
