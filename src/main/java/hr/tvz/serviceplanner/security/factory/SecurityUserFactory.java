package hr.tvz.serviceplanner.security.factory;

import hr.tvz.serviceplanner.security.model.SecurityUser;

import org.springframework.security.core.authority.AuthorityUtils;

import hr.tvz.serviceplanner.persistence.models.User;

public class SecurityUserFactory {
	
	  public static SecurityUser create(User user) {
		  
		    return new SecurityUser(
		      user.getId(),
		      user.getUsername(),
		      user.getPassword(),
		      user.getEmail(),
		      user.getLastPasswordReset(),
		      AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
		    );
		    
		  }
}
