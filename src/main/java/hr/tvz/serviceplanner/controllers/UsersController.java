package hr.tvz.serviceplanner.controllers;

import hr.tvz.serviceplanner.persistence.models.User;
import hr.tvz.serviceplanner.persistence.services.impl.UserServiceImpl;
import hr.tvz.serviceplanner.security.model.SecurityUser;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;

import hr.tvz.serviceplanner.viewmodels.request.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(Authentication authentication) {
		List<User> users = userServiceImpl.findAll();
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		User user = userServiceImpl.findOne(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
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
