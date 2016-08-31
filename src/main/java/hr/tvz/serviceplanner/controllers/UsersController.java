package hr.tvz.serviceplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.serviceplanner.persistence.services.interfaces.UserService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.response.UserViewModel;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserViewModel> getUserById(@PathVariable("id") long id) {
		Long userId = authenticationFacade.getUserId();
		if (userId == null) {
			return new ResponseEntity<UserViewModel>(HttpStatus.UNAUTHORIZED);
		}

		UserViewModel user = UserViewModel.fromUser(userService.findOne(id));
		if (user == null || user.getId() == null) {
			return new ResponseEntity<UserViewModel>(HttpStatus.NOT_FOUND);
		}

		if (userId != user.getId()) {
			return new ResponseEntity<UserViewModel>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<UserViewModel>(user, HttpStatus.OK);
	}
	
}
