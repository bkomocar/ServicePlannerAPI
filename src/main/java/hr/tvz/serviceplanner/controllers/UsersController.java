package hr.tvz.serviceplanner.controllers;

import static org.mockito.Matchers.longThat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.serviceplanner.persistence.models.User;
import hr.tvz.serviceplanner.persistence.services.impl.UserServiceImpl;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.request.RegisterViewModel;
import hr.tvz.serviceplanner.viewmodels.response.UserViewModel;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserViewModel> getUserById(@PathVariable("id") long id) {
		Long userId = authenticationFacade.getUserId();
		if (userId == null) {
			return new ResponseEntity<UserViewModel>(HttpStatus.UNAUTHORIZED);
		}

		UserViewModel user = UserViewModel.fromUser(userServiceImpl.findOne(id));
		if (user == null || user.getId() == null) {
			return new ResponseEntity<UserViewModel>(HttpStatus.NOT_FOUND);
		}

		if (userId != user.getId()) {
			return new ResponseEntity<UserViewModel>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<UserViewModel>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Long> register(@Valid @RequestBody RegisterViewModel model, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Long>(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		if (userServiceImpl.isUserValid(model.getName())) {
			return new ResponseEntity<Long>(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		User user = userServiceImpl.create(RegisterViewModel.toUser(model));
		return new ResponseEntity<Long>(user.getId(), HttpStatus.CREATED);
	}

}
