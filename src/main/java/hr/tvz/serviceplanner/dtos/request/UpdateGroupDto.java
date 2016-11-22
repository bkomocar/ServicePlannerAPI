package hr.tvz.serviceplanner.dtos.request;

import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.enums.GroupType;
import hr.tvz.serviceplanner.persistence.models.Group;

public class UpdateGroupDto {

	@Length(min = 4, max = 255, message = "Name length should be between {min} and {max} characters")
	private String name;

	private GroupType type;

	public UpdateGroupDto() {
		super();
	}

	public UpdateGroupDto(String name, GroupType type) {
		super();
		this.name = name;
		this.type = type;
	}

	public static Group toGroup(UpdateGroupDto model) {
		if (model != null) {
			return new Group(model.name, model.type);
		}
		return null;
	}

	public GroupType getType() {
		return type;
	}

	public void setType(GroupType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
