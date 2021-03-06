package hr.tvz.serviceplanner.security.factory;

import org.springframework.security.core.authority.AuthorityUtils;

import hr.tvz.serviceplanner.persistence.models.User;
import hr.tvz.serviceplanner.security.model.SecurityUser;

public class SecurityUserFactory {

	public static SecurityUser create(User user) {

		return new SecurityUser(user.getId(), user.getName(), user.getPassword(), user.getEmail(),
				user.getLastPasswordReset(), AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities()));

	}
}
