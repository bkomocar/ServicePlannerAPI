package hr.tvz.serviceplanner.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.serviceplanner.persistence.services.interfaces.UserService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.request.UpdateUserViewModel;
import hr.tvz.serviceplanner.viewmodels.response.UserViewModel;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<UserViewModel> getProfile() {
		Long userId = authenticationFacade.getUserId();
		if (userId == null) {
			return new ResponseEntity<UserViewModel>(HttpStatus.UNAUTHORIZED);
		}
		UserViewModel user = UserViewModel.fromUser(userService.findOne(userId));
		if (user == null) {
			return new ResponseEntity<UserViewModel>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserViewModel>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProfile(@Valid @RequestBody UpdateUserViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userId == null) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		if (userService.updateUser(userId, model) != false) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
