package hr.tvz.serviceplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.serviceplanner.dtos.response.UserDto;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
		Long userId = authenticationFacade.getUserId();
		if (userId == null) {
			return new ResponseEntity<UserDto>(HttpStatus.UNAUTHORIZED);
		}

		UserDto user = UserDto.fromUser(userService.findOne(id));
		if (user == null || user.getId() == null) {
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}

		if (userId != user.getId()) {
			return new ResponseEntity<UserDto>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}

}
