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

import hr.tvz.serviceplanner.persistence.services.interfaces.ProductService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.request.CreateProductViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateProductViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;
import hr.tvz.serviceplanner.viewmodels.response.ProductViewModel;

@RestController
@RequestMapping("venues/{venueId}")
public class ProductsController {

	@Autowired
	private UserRightsCheckerService userRightsCheckerService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/categories/{categoryId}/products", method = RequestMethod.POST)
	public ResponseEntity<IdViewModel> createProduct(@PathVariable("venueId") long id,
			@PathVariable("categoryId") long categoryId, @Valid @RequestBody CreateProductViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdViewModel productId = productService.createProduct(categoryId, model);
			if (productId != null) {
				return new ResponseEntity<IdViewModel>(productId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/products/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProduct(@PathVariable("venueId") long id,
			@PathVariable("productId") long productId, @Valid @RequestBody UpdateProductViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (productService.updateProduct(productId, model) != false) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
	public ResponseEntity<ProductViewModel> getProduct(@PathVariable("venueId") long id,
			@PathVariable("productId") long productId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			ProductViewModel model = productService.getProduct(productId);
			if (model != null) {
				return new ResponseEntity<ProductViewModel>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<ProductViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<ProductViewModel>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = "/products/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable("venueId") long id,
			@PathVariable("productId") long productId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			productService.deleteById(productId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}
}
