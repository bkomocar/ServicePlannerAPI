package hr.tvz.serviceplanner.dtos.response;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.User;

public class UserDto {

	private Long id;
	private String name;
	private String email;

	public static UserDto fromUser(User user) {
		if (user != null) {
			return new UserDto(user.getId(), user.getName(), user.getEmail());
		}
		return null;
	}

	public static List<UserDto> fromUser(List<User> users) {
		if (users != null) {
			return users.stream().map(u -> UserDto.fromUser(u)).collect(Collectors.toList());
		}
		return null;
	}

	public UserDto(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
