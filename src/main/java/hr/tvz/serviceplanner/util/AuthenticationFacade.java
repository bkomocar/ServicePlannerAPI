package hr.tvz.serviceplanner.util;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();
    Long getUserId();
}

