package hr.tvz.serviceplanner.util;

import org.springframework.security.core.Authentication;

import hr.tvz.serviceplanner.persistence.models.User;

public interface AuthenticationFacade {
	Authentication getAuthentication();

	Long getUserId();

	User getUser();
}
