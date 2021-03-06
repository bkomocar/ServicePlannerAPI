package hr.tvz.serviceplanner.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.serviceplanner.dtos.request.AuthenticationDto;
import hr.tvz.serviceplanner.dtos.request.RegisterDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.dtos.response.TokenDto;
import hr.tvz.serviceplanner.persistence.services.impl.UserServiceImpl;
import hr.tvz.serviceplanner.security.TokenUtils;
import hr.tvz.serviceplanner.security.model.SecurityUser;

@RestController
@RequestMapping("${route.authentication}")
public class AuthenticationController {

	@Value("${token.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserServiceImpl userDetailsService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<IdDto> register(@Valid @RequestBody RegisterDto model) {
		if (userDetailsService.isUserValid(model.getUsername())) {
			return new ResponseEntity<IdDto>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		IdDto idDto = userDetailsService.saveUser(RegisterDto.toUser(model));
		if (idDto == null) {
			return new ResponseEntity<IdDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<IdDto>(idDto, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@Valid @RequestBody AuthenticationDto authenticationRequest, Device device)
			throws AuthenticationException {
		Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = this.tokenUtils.generateToken(userDetails, device);

		// Return the token
		return ResponseEntity.ok(new TokenDto(token));
	}

	@RequestMapping(value = "${route.authentication.refresh}", method = RequestMethod.GET)
	public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
		String token = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(token);
		SecurityUser user = (SecurityUser) this.userDetailsService.loadUserByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
			String refreshedToken = this.tokenUtils.refreshToken(token);
			return ResponseEntity.ok(new TokenDto(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
}
