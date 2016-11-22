package hr.tvz.serviceplanner.persistence.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.dtos.request.UpdateUserDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.dtos.response.UserDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.UserDao;
import hr.tvz.serviceplanner.persistence.models.User;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserService;
import hr.tvz.serviceplanner.security.factory.SecurityUserFactory;

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService, UserDetailsService {

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl() {
		super();
	}

	public List<UserDto> getAllUsers() {
		return UserDto.fromUser(getDao().findAll());
	}

	@Override
	public IdDto saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User daoUser = getDao().create(user);
		if (daoUser != null) {
			return new IdDto(daoUser.getId());
		}
		return null;
	}

	@Override
	protected Operations<User> getDao() {
		return dao;
	}

	@Override
	public boolean isUserValid(String name) {
		if (dao.findByName(name) != null) {
			return true;
		}
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.dao.findByName(username);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return SecurityUserFactory.create(user);
		}
	}

	@Override
	public boolean updateUser(Long userId, UpdateUserDto model) {
		if (model != null) {
			User user = UpdateUserDto.toUser(model);
			if (user.getPassword() != null) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			return dao.updateUser(userId, user);
		}
		return false;
	}
}