package hr.tvz.serviceplanner.viewmodels.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.enums.GroupType;
import hr.tvz.serviceplanner.persistence.models.Group;

public class CreateGroupViewModel {

	@NotNull(message = "Name is a required field")
	@Length(min = 4, max = 255, message = "Name length should be between {min} and {max} characters")
	private String name;

	@NotNull(message = "Type is a required field")
	private GroupType type;

	public CreateGroupViewModel() {
		super();
	}

	public CreateGroupViewModel(String name, GroupType type) {
		super();
		this.name = name;
		this.type = type;
	}

	public static Group toGroup(CreateGroupViewModel model) {
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
