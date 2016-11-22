package hr.tvz.serviceplanner.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.serviceplanner.dtos.request.UpdateUserDto;
import hr.tvz.serviceplanner.dtos.response.UserDto;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<UserDto> getProfile() {
		Long userId = authenticationFacade.getUserId();
		if (userId == null) {
			return new ResponseEntity<UserDto>(HttpStatus.UNAUTHORIZED);
		}
		UserDto user = UserDto.fromUser(userService.findOne(userId));
		if (user == null) {
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProfile(@Valid @RequestBody UpdateUserDto model) {
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
