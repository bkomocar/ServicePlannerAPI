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

import hr.tvz.serviceplanner.dtos.PurchaseDto;
import hr.tvz.serviceplanner.dtos.request.CreatePurchaseDto;
import hr.tvz.serviceplanner.dtos.request.UpdatePurchaseDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.services.interfaces.PurchaseService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;

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
	public ResponseEntity<IdDto> createPurchase(@PathVariable("venueId") long id,
			@Valid @RequestBody CreatePurchaseDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdDto productId = purchaseService.createPurchase(id, model);
			if (productId != null) {
				return new ResponseEntity<IdDto>(productId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdDto>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{purchaseId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updatePurchase(@PathVariable("venueId") long id,
			@PathVariable("purchaseId") long purchaseId, @Valid @RequestBody UpdatePurchaseDto model) {
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
	public ResponseEntity<PurchaseDto> getPurchase(@PathVariable("venueId") long id,
			@PathVariable("purchaseId") long purchaseId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			PurchaseDto model = purchaseService.getPurchase(purchaseId);
			if (model != null) {
				return new ResponseEntity<PurchaseDto>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<PurchaseDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<PurchaseDto>(HttpStatus.FORBIDDEN);
		}
	}

	/* date in yyyy-MM-dd */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PurchaseDto>> getPurchases(@PathVariable("venueId") long id,
			@RequestParam(name = "groupId", required = false) Long groupId,
			@RequestParam(name = "date", required = false) String date) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			List<PurchaseDto> models = purchaseService.getPurchases(id, groupId, date);
			if (models != null) {
				return new ResponseEntity<List<PurchaseDto>>(models, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<PurchaseDto>>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<List<PurchaseDto>>(HttpStatus.FORBIDDEN);
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
