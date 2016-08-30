package hr.tvz.serviceplanner.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import hr.tvz.serviceplanner.security.model.SecurityUser;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public Long getUserId() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser user = (SecurityUser) authentication.getPrincipal();

		if (user != null && user.getId() != null) {
			return user.getId();
		}
		return null;
	}
}