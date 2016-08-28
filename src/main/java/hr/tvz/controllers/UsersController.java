package hr.tvz.controllers;

import hr.tvz.persistence.models.User;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import hr.tvz.viewmodels.request.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import hr.tvz.persistence.services.impl.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		User user = userService.findOne(id);
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

		if (userService.isUserValid(model.getName())) {
			return new ResponseEntity<Long>(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		User user = userService.create(RegisterViewModel.toUser(model));
		return new ResponseEntity<Long>(user.getId(), HttpStatus.CREATED);
	}
}
